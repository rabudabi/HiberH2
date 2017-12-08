package dao;

import dataSets.ItemDataSet;
import dataSets.NodeDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;

public class NodeDAO {
    private Session session;

    public NodeDAO(Session session) {
        this.session = session;
    }

    public<T> NodeDataSet get(T item) throws HibernateException {
        if(item instanceof String){
            String name = (String) item;
            Criteria criteria = session.createCriteria(NodeDataSet.class);
            return (NodeDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult();
        }
        else {
            Long id = (Long) item;
            return (NodeDataSet) session.get(NodeDataSet.class, id);
        }

    }


    public long getNodeId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(NodeDataSet.class);
        return ((NodeDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
    }


    public List<NodeDataSet> getAllNodes() {
        Criteria criteria = session.createCriteria(NodeDataSet.class);
        return (List<NodeDataSet>) criteria.list();
    }


    public long insertNode(String name) throws HibernateException {
        return (Long) session.save(new NodeDataSet(name));
    }

    public long clearTable() {
        Query query = session.createQuery("DELETE FROM NodeDataSet");
        return query.executeUpdate();
    }
}

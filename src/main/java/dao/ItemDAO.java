package dao;

import dataSets.ItemDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ItemDAO {
    private Session session;

    public ItemDAO(Session session) {
        this.session = session;
    }


    public<T> ItemDataSet get(T id) throws HibernateException {
        if(id instanceof String){
            Criteria criteria = session.createCriteria(ItemDataSet.class);
            return ((ItemDataSet) criteria.add(Restrictions.eq("name", id)).uniqueResult());
        } else {
            Long item = (Long) id;
            return (ItemDataSet) session.get(ItemDataSet.class, item);
        }

    }


    public List<ItemDataSet> getItemListForNode(long id) throws HibernateException{
        Criteria criteria = session.createCriteria(ItemDataSet.class);
        return (List<ItemDataSet>) criteria.add(Restrictions.eq("node_id", id)).list();
    }


    public long insertItem(String name, double price, long node_id) throws HibernateException {
        return (Long) session.save(new ItemDataSet(name, price, node_id));
    }

    public long clearTable() {
        Query query = session.createQuery("DELETE FROM ItemDataSet");
        return query.executeUpdate();
    }
}

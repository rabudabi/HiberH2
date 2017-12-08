package dbService;

import dao.ItemDAO;
import dao.NodeDAO;
import dataSets.ItemDataSet;
import dataSets.NodeDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class DBNodeService {

    private final SessionFactory sessionFactory;
    public DBNodeService() {
        sessionFactory = createSessionFactory(new H2Conf().getH2Configuration());
    }


    public<T> NodeDataSet getNode(T id) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            NodeDAO dao = new NodeDAO(session);
            NodeDataSet dataSet = dao.get(id);
            session.close();
            return dataSet;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public boolean chekNode(String name) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            long item_id = new NodeDAO(session).get(name).getId();
            session.close();
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public List<NodeDataSet> getAllNodes() throws DBException{
        try {
            Session session = sessionFactory.openSession();
            NodeDAO dao = new NodeDAO(session);
            List<NodeDataSet> dataSets = dao.getAllNodes();
            session.close();
            return dataSets;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }


    public long addNode(String name) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            NodeDAO dao = new NodeDAO(session);
            long id = dao.insertNode(name);
            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public void clearAllNodes() throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            new NodeDAO(session).clearTable();
            transaction.commit();
            session.close();

        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }


    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}

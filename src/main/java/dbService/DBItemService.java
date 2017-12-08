package dbService;

import dao.ItemDAO;
import dao.NodeDAO;
import dataSets.ItemDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class DBItemService {

    private final SessionFactory sessionFactory;

    public DBItemService() {
        sessionFactory = createSessionFactory(new H2Conf().getH2Configuration());
    }


    public<T> ItemDataSet getItem(T id) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            ItemDAO dao = new ItemDAO(session);
            ItemDataSet dataSet = dao.get(id);
            session.close();
            return dataSet;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public boolean chekItem(String name) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            long item_id = new ItemDAO(session).get(name).getId();
            session.close();
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public List<ItemDataSet> getAllItemsForNode(long id) throws DBException{
        try {
            Session session = sessionFactory.openSession();
            ItemDAO dao = new ItemDAO(session);
            List<ItemDataSet> dataSets = dao.getItemListForNode(id);
            session.close();
            return dataSets;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }


    public long addItem(String name, double price, long node_id) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            ItemDAO dao = new ItemDAO(session);
            long id = dao.insertItem(name, price, node_id);
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
            new ItemDAO(session).clearTable();
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
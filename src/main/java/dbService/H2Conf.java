package dbService;

import dataSets.ItemDataSet;
import dataSets.NodeDataSet;
import org.hibernate.cfg.Configuration;

public class H2Conf {

    private Configuration configuration;


    public  H2Conf() {
        configuration = new Configuration();
        configuration.addAnnotatedClass(ItemDataSet.class);
        configuration.addAnnotatedClass(NodeDataSet.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:~/test");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "false");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
    }


    public Configuration getH2Configuration(){
        return configuration;
    }
}

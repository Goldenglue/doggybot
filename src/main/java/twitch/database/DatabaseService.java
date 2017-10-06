package twitch.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import twitch.dataobjects.TwitchChannel;

public class DatabaseService {
    private final SessionFactory factory;

    public DatabaseService() {
        Configuration configuration = createConfiguration();
        factory = configuration.buildSessionFactory();
    }

    private Configuration createConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:~/goggysdb");
        configuration.setProperty("hibernate.connection.username", "doggybot");
        configuration.setProperty("hibernate.connection.password", "woofwoof");
        configuration.setProperty("hibernate.show_sql", "false");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.jdbc.batch_size","0");
        configuration.addAnnotatedClass(TwitchChannel.class);
        return configuration;
    }

    public SessionFactory getFactory() {
        return factory;
    }
}

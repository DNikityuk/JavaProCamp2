package org.procamp.factory;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.procamp.model.entity.*;


public class SessionFactoryUtil {

    private static SessionFactory instance;


    private SessionFactoryUtil() {

    }

    public static SessionFactory getInstance() {
        if (instance == null) {
            buildSessionFactory();
        }
        return instance;
    }

    public static void buildSessionFactory() {
//        Configuration cfg = new Configuration()
//                .configure(new File("C:\\Users\\nikit\\IdeaProjects\\ProCamp\\HibernateJava\\src\\main\\resources\\hibernate.cfg.xml"));
//        StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
//        sb.applySettings(cfg.getProperties());
//        StandardServiceRegistry standardServiceRegistry = sb.build();
//        return cfg.buildSessionFactory(standardServiceRegistry);

        Configuration configuration = new Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Report.class)
                .addAnnotatedClass(Building.class)
                .addAnnotatedClass(Activity.class)
                .addAnnotatedClass(Material.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        instance = configuration.buildSessionFactory(serviceRegistry);
    }

}

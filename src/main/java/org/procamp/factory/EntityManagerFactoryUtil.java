package org.procamp.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryUtil {

    private static EntityManagerFactory instance;

    private EntityManagerFactoryUtil() {
    }

    public static EntityManager getEntityManager() {
        if (instance == null) {
            buildEntityManagerFactory();
        }
        return instance.createEntityManager();
    }

    public static void buildEntityManagerFactory() {
        instance =  Persistence.createEntityManagerFactory("org.procamp.data.hibernate");
    }
}

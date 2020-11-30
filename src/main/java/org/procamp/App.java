package org.procamp;

import org.procamp.factory.EntityManagerFactoryUtil;
import org.procamp.factory.SessionFactoryUtil;
import org.procamp.model.entity.Activity;
import org.procamp.model.entity.Report;
import org.procamp.service.ActivityService;
import org.procamp.service.BuildingService;
import org.procamp.service.ReportService;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        SessionFactoryUtil.buildSessionFactory();
        EntityManagerFactoryUtil.buildEntityManagerFactory();

    }
}

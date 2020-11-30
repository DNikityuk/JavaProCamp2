package org.procamp.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.procamp.dao.IBuildingDAO;
import org.procamp.dao.IUserDAO;
import org.procamp.factory.DAOFactory;
import org.procamp.model.entity.Activity;
import org.procamp.model.entity.Building;
import org.procamp.model.entity.Report;
import org.procamp.model.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.procamp.AppConstants.DAO_INJECT;

public class BuildingServiceTest {


    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    private DAOFactory daoFactory;

    private BuildingService buildingService;
    private User user1, user2, user3;
    private Report report11, report12, report21, report22, report31, report32;
    private Building building111, building112, building121, building211, building221, building222, building311, building321;
    private Activity activity1111, activity1121, activity1122, activity1211, activity2111, activity2211,
            activity2212, activity2221, activity2222, activity3111, activity3211, activity3212;

    @Before
    public void setUp() throws ParseException {
        daoFactory = DAOFactory.getDAOFactory(DAO_INJECT);
        daoFactory.getDataSource().startTransaction();

        final IUserDAO userDAO = daoFactory.getUserDAO();

        user1 = new User("Karlo", "Terrius", "faggwvxcw@gmail.com", "vsfds23vs32");
        user2 = new User("Evos", "Fueggo", "brtjyue@gmail.com", "asa22da111f");
        user3 = new User("Guest", "Tivos", "bcvcxzfw@gmail.com", "21db43bbs3");

        report11 = new Report(user1, "Acwevz", 59d, dateFormat.parse("2020-10-21 14:12"));
        report12 = new Report(user1, "Vssdvs adsaff", 164d, dateFormat.parse("2020-10-24 11:48"));
        user1.setReports(new ArrayList<>(Arrays.asList(report11, report12)));

        report21 = new Report(user2, "Hdfgssdfc", 235d, dateFormat.parse("2020-10-23 15:11"));
        report22 = new Report(user2, "Hbxza qweewfv", 129d, dateFormat.parse("2020-10-26 13:15"));
        user2.setReports(new ArrayList<>(Arrays.asList(report21, report22)));

        report31 = new Report(user3, "Vsfzderfv faqe", 321d, dateFormat.parse("2020-10-29 17:28"));
        report32 = new Report(user3, "Afavzvxz weretgv", 97d, dateFormat.parse("2020-10-27 12:40"));
        user3.setReports(new ArrayList<>(Arrays.asList(report31, report32)));


        building111 = new Building(report11, "Favcvreter", true);
        building112 = new Building(report11, "Bsdfweszz", true);
        building121 = new Building(report12, "Asdageevvz", true);
        report11.setBuildings(new ArrayList<>(Arrays.asList(building111, building112)));
        report12.setBuildings(new ArrayList<>(Arrays.asList(building121)));

        building211 = new Building(report21, "Bczxcwerhgfj", true);
        building221 = new Building(report22, "Vzcwrtyv", true);
        building222 = new Building(report22, "Bvzfwyiikip", true);
        report21.setBuildings(new ArrayList<>(Arrays.asList(building211)));
        report22.setBuildings(new ArrayList<>(Arrays.asList(building221, building222)));

        building311 = new Building(report31, "Vzxzxccefqwq", true);
        building321 = new Building(report32, "Mvmbnfdfa", true);
        report31.setBuildings(new ArrayList<>(Arrays.asList(building311)));
        report32.setBuildings(new ArrayList<>(Arrays.asList(building321)));


        activity1111 = new Activity(building111, "vdfvz cz zxcz", "fd", 49d, 4d);
        activity1121 = new Activity(building112, "vxveryr rtyhx xda", "vz", 150d, 1d);
        activity1122 = new Activity(building112, "bfit wef2v weaf", "fd", 130d, 3d);
        activity1211 = new Activity(building121, "vsgs dfghjj wewf", "ld", 180d, 3d);
        building111.setActivities(new ArrayList<>(Arrays.asList(activity1111)));
        building112.setActivities(new ArrayList<>(Arrays.asList(activity1121, activity1122)));
        building121.setActivities(new ArrayList<>(Arrays.asList(activity1211)));

        activity2111 = new Activity(building211, "yerwf sfd jkijd", "fd", 400d, 1d);
        activity2211 = new Activity(building221, "bhe4terwtvb sdfascdc", "vz", 120d, 5d);
        activity2212 = new Activity(building221, "jytirtgdffvf", "fd", 195d, 2d);
        activity2221 = new Activity(building222, "bdfwertwreyery", "ld", 178d, 3d);
        activity2222 = new Activity(building222, "vxcvknsdifiehf sf", "ld", 235d, 2d);
        building211.setActivities(new ArrayList<>(Arrays.asList(activity2111)));
        building221.setActivities(new ArrayList<>(Arrays.asList(activity2211, activity2212)));
        building222.setActivities(new ArrayList<>(Arrays.asList(activity2221, activity2222)));

        activity3111 = new Activity(building311, "sdjgbs sdglsd sds", "fd", 312d, 3d);
        activity3211 = new Activity(building321, "ryrhev dvsc", "md", 321d, 4d);
        activity3212 = new Activity(building321, "jytirtg grdffvf", "fd", 183d, 1d);
        building311.setActivities(new ArrayList<>(Arrays.asList(activity3111)));
        building321.setActivities(new ArrayList<>(Arrays.asList(activity3211, activity3212)));

        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);

        daoFactory.getDataSource().flush();

        buildingService = new BuildingService();
    }

    @After
    public void tearDown() {
        daoFactory.getDataSource().rollbackTransaction();
        daoFactory.getDataSource().releaseConnection();
    }


    @Test
    public void deactivateBuildingsWitchTotalPriceActivitiesMoreThanTest1() {

        buildingService.deactivateBuildingsWitchTotalPriceActivitiesMoreThan(20d, daoFactory);

        IBuildingDAO buildingDAO = daoFactory.getBuildingDAO();

        buildingDAO.refreshAll(new ArrayList<>(Arrays.asList(building111, building112, building121, building211, building221, building222, building311, building321)));


        assertFalse(building111.isActive());
        assertFalse(building112.isActive());
        assertFalse(building121.isActive());
        assertFalse(building211.isActive());
        assertFalse(building221.isActive());
        assertFalse(building222.isActive());
        assertFalse(building311.isActive());
        assertFalse(building321.isActive());
    }


    @Test
    public void deactivateBuildingsWitchTotalPriceActivitiesMoreThanTest2() {

        buildingService.deactivateBuildingsWitchTotalPriceActivitiesMoreThan(600d, daoFactory);

        IBuildingDAO buildingDAO = daoFactory.getBuildingDAO();
        buildingDAO.refreshAll(new ArrayList<>(Arrays.asList(building111, building112, building121, building211, building221, building222, building311, building321)));


        assertTrue(building111.isActive());
        assertTrue(building112.isActive());
        assertTrue(building121.isActive());
        assertTrue(building211.isActive());
        assertTrue(building221.isActive());
        assertTrue(building222.isActive());
        assertTrue(building311.isActive());
        assertTrue(building321.isActive());
    }


    @Test
    public void deactivateBuildingsWitchTotalPriceActivitiesMoreThanTest3() {

        buildingService.deactivateBuildingsWitchTotalPriceActivitiesMoreThan(314d, daoFactory);

        IBuildingDAO buildingDAO = daoFactory.getBuildingDAO();
        buildingDAO.refreshAll(new ArrayList<>(Arrays.asList(building111, building112, building121, building211, building221, building222, building311, building321)));


        assertTrue(building111.isActive());
        assertTrue(building112.isActive());
        assertTrue(building121.isActive());
        assertFalse(building211.isActive());
        assertFalse(building221.isActive());
        assertFalse(building222.isActive());
        assertTrue(building311.isActive());
        assertFalse(building321.isActive());
    }


}

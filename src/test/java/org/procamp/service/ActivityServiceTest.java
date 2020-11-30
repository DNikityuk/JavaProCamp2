package org.procamp.service;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
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

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.procamp.AppConstants.DAO_INJECT;

public class ActivityServiceTest {


    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    private DAOFactory daoFactory;

    private ActivityService activityService;
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

        activityService = new ActivityService();
    }

    @After
    public void tearDown() {
        daoFactory.getDataSource().rollbackTransaction();
        daoFactory.getDataSource().releaseConnection();
    }


    @Test
    public void getAllActivitiesByUserIdAndBuildingIdTest() {

        Activity[] activitiesByUser1AndBuilding112 = new Activity[]{activity1121, activity1122};
        assertThat(
                activityService.getAllActivitiesByUserIdAndBuildingId(user1.getUserId(), building112.getBuildingId(), daoFactory),
                containsInAnyOrder(activitiesByUser1AndBuilding112)
        );

        Activity[] activitiesByUser1AndBuilding121 = new Activity[]{activity1211};
        assertThat(
                activityService.getAllActivitiesByUserIdAndBuildingId(user1.getUserId(), building121.getBuildingId(), daoFactory),
                containsInAnyOrder(activitiesByUser1AndBuilding121)
        );

        Activity[] activitiesByUser2AndBuilding221 = new Activity[]{activity2211, activity2212};
        assertThat(
                activityService.getAllActivitiesByUserIdAndBuildingId(user2.getUserId(), building221.getBuildingId(), daoFactory),
                containsInAnyOrder(activitiesByUser2AndBuilding221)
        );

        Activity[] activitiesByUser2AndBuilding222 = new Activity[]{activity2222, activity2221};
        assertThat(
                activityService.getAllActivitiesByUserIdAndBuildingId(user2.getUserId(), building222.getBuildingId(), daoFactory),
                containsInAnyOrder(activitiesByUser2AndBuilding222)
        );

        Activity[] activitiesByUser3AndBuilding311 = new Activity[]{activity3111};
        assertThat(
                activityService.getAllActivitiesByUserIdAndBuildingId(user3.getUserId(), building311.getBuildingId(), daoFactory),
                containsInAnyOrder(activitiesByUser3AndBuilding311)
        );

        Activity[] activitiesByUser3AndBuilding321 = new Activity[]{activity3211, activity3212};
        assertThat(
                activityService.getAllActivitiesByUserIdAndBuildingId(user3.getUserId(), building321.getBuildingId(), daoFactory),
                containsInAnyOrder(activitiesByUser3AndBuilding321)
        );


        assertThat(
                activityService.getAllActivitiesByUserIdAndBuildingId(user1.getUserId(), building321.getBuildingId(), daoFactory),
                CoreMatchers.is(empty())
        );

        assertThat(
                activityService.getAllActivitiesByUserIdAndBuildingId(user2.getUserId(), building121.getBuildingId(), daoFactory),
                CoreMatchers.is(empty())
        );

        assertThat(
                activityService.getAllActivitiesByUserIdAndBuildingId(user3.getUserId(), building221.getBuildingId(), daoFactory),
                CoreMatchers.is(empty())
        );

        assertThat(
                activityService.getAllActivitiesByUserIdAndBuildingId(null, building221.getBuildingId(), daoFactory),
                CoreMatchers.is(empty())
        );

        assertThat(
                activityService.getAllActivitiesByUserIdAndBuildingId(user3.getUserId(), null, daoFactory),
                CoreMatchers.is(empty())
        );

        assertThat(
                activityService.getAllActivitiesByUserIdAndBuildingId(null, null, daoFactory),
                CoreMatchers.is(empty())
        );
    }


    @Test
    public void getTotalActivitiesPriceByBuildingIdTest() {

        assertThat(
                activityService.getTotalActivitiesPriceByBuildingId(building311.getBuildingId(), daoFactory),
                CoreMatchers.is(312d)
        );
        assertThat(
                activityService.getTotalActivitiesPriceByBuildingId(building321.getBuildingId(), daoFactory),
                CoreMatchers.is(504d)
        );
        assertThat(
                activityService.getTotalActivitiesPriceByBuildingId(building221.getBuildingId(), daoFactory),
                CoreMatchers.is(315d)
        );
        assertThat(
                activityService.getTotalActivitiesPriceByBuildingId(building222.getBuildingId(), daoFactory),
                CoreMatchers.is(413d)
        );
        assertThat(
                activityService.getTotalActivitiesPriceByBuildingId(building112.getBuildingId(), daoFactory),
                CoreMatchers.is(280d)
        );
        assertThat(
                activityService.getTotalActivitiesPriceByBuildingId(building111.getBuildingId(), daoFactory),
                CoreMatchers.is(49d)
        );
        assertThat(
                activityService.getTotalActivitiesPriceByBuildingId(Long.MAX_VALUE, daoFactory),
                CoreMatchers.nullValue()
        );
        assertThat(
                activityService.getTotalActivitiesPriceByBuildingId(null, daoFactory),
                CoreMatchers.nullValue()
        );
    }


    @Test
    public void getTotalActivitiesPriceByReportIdTest() {

        assertThat(
                activityService.getTotalActivitiesPriceByReportId(report11.getReportId(), daoFactory),
                CoreMatchers.is(329d)
        );
        assertThat(
                activityService.getTotalActivitiesPriceByReportId(report12.getReportId(), daoFactory),
                CoreMatchers.is(180d)
        );
        assertThat(
                activityService.getTotalActivitiesPriceByReportId(report21.getReportId(), daoFactory),
                CoreMatchers.is(400d)
        );
        assertThat(
                activityService.getTotalActivitiesPriceByReportId(report22.getReportId(), daoFactory),
                CoreMatchers.is(728d)
        );
        assertThat(
                activityService.getTotalActivitiesPriceByReportId(report31.getReportId(), daoFactory),
                CoreMatchers.is(312d)
        );
        assertThat(
                activityService.getTotalActivitiesPriceByReportId(report32.getReportId(), daoFactory),
                CoreMatchers.is(504d)
        );
        assertThat(
                activityService.getTotalActivitiesPriceByReportId(Long.MAX_VALUE, daoFactory),
                CoreMatchers.nullValue()
        );
        assertThat(
                activityService.getTotalActivitiesPriceByReportId(null, daoFactory),
                CoreMatchers.nullValue()
        );
    }


    @Test
    public void getTotalActivitiesPriceByUserIdTest() {

        assertThat(
                activityService.getTotalActivitiesPriceByUserId(user1.getUserId(), daoFactory),
                CoreMatchers.is(509d)
        );
        assertThat(
                activityService.getTotalActivitiesPriceByUserId(user2.getUserId(), daoFactory),
                CoreMatchers.is(1128d)
        );
        assertThat(
                activityService.getTotalActivitiesPriceByUserId(user3.getUserId(), daoFactory),
                CoreMatchers.is(816d)
        );
        assertThat(
                activityService.getTotalActivitiesPriceByUserId(Long.MAX_VALUE, daoFactory),
                CoreMatchers.nullValue()
        );
        assertThat(
                activityService.getTotalActivitiesPriceByUserId(null, daoFactory),
                CoreMatchers.nullValue()
        );
    }


}

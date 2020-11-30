package org.procamp.service;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.procamp.dao.IUserDAO;
import org.procamp.factory.DAOFactory;
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

public class ReportServiceTest {


    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    private DAOFactory daoFactory;

    private ReportService reportService;
    private User user1, user2, user3;
    private Report report11, report12, report21, report22, report31, report32;


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

        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);

        daoFactory.getDataSource().flush();

        reportService = new ReportService();
    }

    @After
    public void tearDown() {
        daoFactory.getDataSource().rollbackTransaction();
        daoFactory.getDataSource().releaseConnection();
    }


    @Test
    public void getAllReportsByUserIdTest() {

        Report[] allReportsByUser1 = new Report[]{report12, report11};
        assertThat(
                reportService.getAllReportsByUserId(user1.getUserId(), daoFactory),
                containsInAnyOrder(allReportsByUser1)
        );

        Report[] allReportsByUser2 = new Report[]{report22, report21};
        assertThat(
                reportService.getAllReportsByUserId(user2.getUserId(), daoFactory),
                containsInAnyOrder(allReportsByUser2)
        );

        Report[] allReportsByUser3 = new Report[]{report31, report32};
        assertThat(
                reportService.getAllReportsByUserId(user3.getUserId(), daoFactory),
                containsInAnyOrder(allReportsByUser3)
        );


        assertThat(
                reportService.getAllReportsByUserId(null, daoFactory),
                CoreMatchers.is(empty())
        );

        assertThat(
                reportService.getAllReportsByUserId(Long.MAX_VALUE, daoFactory),
                CoreMatchers.is(empty())
        );


    }
}

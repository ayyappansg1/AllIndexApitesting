package com.qa.hcm.api.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;

import com.qa.hcm.api.tests.createPortfolio;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class BaseTest {
    private final TestUtil testUtil = new TestUtil();

    private static final Logger logger = LoggerFactory.getLogger(createPortfolio.class);
    Properties prop = testUtil.init_Properties("Dashboard");

    @BeforeClass(alwaysRun = true)
    public void init() {
    }
    public static String getTodayDate(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = now.format(formatter);
        System.out.println(formattedDateTime);
        return formattedDateTime;
    }
    public String getNoOfStocksDividingBasedonHundredPercent() {
    	logger.info("List of Stocks divided based on number of stocks");
    	double  percentage=1;
    	double noOfStocks=Double.parseDouble(prop.getProperty("noOfStocks"));
    	NumberFormat formatter = new DecimalFormat("0.000000");
    	return  formatter.format(percentage/noOfStocks);
    }

}

package com.qa.hcm.api.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.qa.hcm.api.auth.Authentication;
import com.qa.hcm.api.common.BaseTest;
import com.qa.hcm.api.common.RestClient;
import com.qa.hcm.api.common.TestUtil;
import com.qa.hcm.api.pojo.*;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class createPortfolio extends BaseTest {
	private static final Logger logger = LoggerFactory.getLogger(createPortfolio.class);
	private final TestUtil testUtil = new TestUtil();
	Faker faker = new Faker();

	Authentication authentication = new Authentication();
	Properties prop = testUtil.init_Properties("Dashboard");
	Properties auth = testUtil.init_Properties("Authorization");
	Properties random;


	@Test(groups = {"Smoke"},priority = -2)
	public void createPortFolio() throws IOException {
		logger.info("### Running createPortFolio Method  ###");
		String createPortfolio = prop.getProperty("BASE_PATH_CREATEPORTFOLIO");

		// Authorization token
		Map<String, String> authToken = new HashMap<>();
		authToken.put("Authorization", authentication.getAccessTokenDirectlywithUserNamePassword());

		CreatePortFolioPojo createPortFolioPojo=new CreatePortFolioPojo();
		Ranking ranking=new Ranking();
		createPortFolioPojo.setRanking(ranking);
		Filter filter=new Filter();
		createPortFolioPojo.setFilter(filter);
		createPortFolioPojo.setExclusions(null);
		Weights weights=new Weights();
		weights.setInstruments(null);
		weights.setWeightId(4);
		weights.setReset_to("Equal");
		weights.setWeighting_type(4);
		createPortFolioPojo.setWeights(weights);
		Instrument instrument=new Instrument();
		instrument.setTicker(prop.getProperty("stock1"));
		instrument.setPercentageweight(0.333333);
		Instrument instrument2=new Instrument();
		instrument2.setTicker("AAPL.US");
		instrument2.setPercentageweight(0.333333);
		Instrument instrument3=new Instrument();
		instrument3.setTicker("CERN.US");
		instrument3.setPercentageweight(0.333333);
		ArrayList<Instrument> li=new ArrayList<>();
		li.add(instrument);
		li.add(instrument2);
		li.add(instrument3);
		createPortFolioPojo.setInstruments(li);
		Portfolio portfolio=new Portfolio();
		portfolio.setCurrency(2);
		portfolio.setLaunch_date(getTodayDate());
		portfolio.setBacktest_available_since(getTodayDate());
		portfolio.setNo_of_stock(3);
		portfolio.setRebalance_frequency(3);
		ArrayList<Integer> environmentList=new ArrayList<>();
		environmentList.add(0);
		environmentList.add(100);
		ArrayList<Integer> scoialList=new ArrayList<>();
		scoialList.add(0);
		scoialList.add(100);
		ArrayList<Integer> governanceList=new ArrayList<>();
		governanceList.add(0);
		governanceList.add(100);
		Esg esg=new Esg();
		esg.setEnvironment(environmentList);
		esg.setGovernance(governanceList);
		esg.setSocial(scoialList);
		portfolio.setEsg(esg);
		ArrayList<Integer> marketKapList=new ArrayList<>();
		marketKapList.add(0);
		marketKapList.add(3000);
		portfolio.setMarket_cap(marketKapList);
		portfolio.setWeighting_type(4);
		portfolio.setResearch_portfolio(true);
		portfolio.setRebalance(false);
		ArrayList<Integer> indexTypeList=new ArrayList<>();
		indexTypeList.add(1);
		portfolio.setIndex_type(indexTypeList);
		portfolio.setPeriod(6);
		MetaData data=new MetaData();
		portfolio.setMeta_data(data);
		portfolio.setName(faker.name().firstName()+"_API Automated");
		portfolio.setDescription(faker.book().genre());
		createPortFolioPojo.setPortfolio(portfolio);

		ObjectMapper objectMapper = new ObjectMapper();
		String writeValueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(createPortFolioPojo);

		Response actualResponse = RestClient.doPost("JSON", authToken, null, createPortfolio, true, writeValueAsString);
		System.out.println(actualResponse.asPrettyString());
		Object object = actualResponse.jsonPath().get("msg");
		Assert.assertEquals((String)object, "Hurray! Once we finish baking your freshly brewed portfolio, it will be visible in the MY UNIVERSE section , usually in 5-10 seconds!");
		Assert.assertEquals(actualResponse.statusCode(), 200);
		//   JSONAssert.assertEquals(expectedResponse,actualResponse.asString(), JSONCompareMode.STRICT);
	}

}
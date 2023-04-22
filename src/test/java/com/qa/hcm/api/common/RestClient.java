package com.qa.hcm.api.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class RestClient {
    private static final TestUtil testUtil = new TestUtil();

    //static Properties applyTimeOffProperties = testUtil.init_Properties("ApplyTimeOff");
    //static Properties applyTTprop = testUtil.init_Properties("ApplyTimeTracking");



    public static Response doGet(String baseURI, String contentType, Map<String, String> Token,
                                 Map<String, String> paramMaps, String basePath, boolean log) {
        if (setBaseURI(baseURI)) {
            RequestSpecification request = createRequest(log, contentType, Token, paramMaps);
            return getResponse("GET", request, basePath);
        }
        return null;
    }

    public static Response doPost(String contentType, Map<String, String> Token,
                                  Map<String, String> paramMaps, String basePath, boolean log, String payLoad) {

            RequestSpecification request = createRequest(log, contentType, Token, paramMaps);
            request.body(payLoad);
            return getResponse("POST", request, basePath);
    }

    public static Response doPut(String baseURI, String contentType, Map<String, String> Token,
                                 Map<String, String> paramMaps, String basePath, boolean log, String payLoad) {

        if (setBaseURI(baseURI)) {
            RequestSpecification request = createRequest(log, contentType, Token, paramMaps);
            if(payLoad!=null){request.body(payLoad);}
            return getResponse("PUT", request, basePath);
        }
        return null;
    }

    public static Response doDelete(String baseURI, String contentType, Map<String, String> Token,
                                    Map<String, String> paramMaps, String basePath, boolean log, Object obj) throws JsonProcessingException {

        if (setBaseURI(baseURI)) {
            RequestSpecification request = createRequest(log, contentType, Token, paramMaps);
            if(obj!=null){ addRequestPayLoad(request, obj);}
            return getResponse("DELETE", request, basePath);
        }
        return null;
    }

    public static void addRequestPayLoad(RequestSpecification request, Object obj) throws JsonProcessingException {
        if (obj instanceof Map) {
            request.formParams((Map<String, String>) obj);
        } else {
            String jsonPayLoad = TestUtil.getSerializedJson(obj);
            request.body(jsonPayLoad);
        }
    }

    private static boolean setBaseURI(String baseURI) {
        if (baseURI == null || baseURI.isEmpty()) {
            return false;
        }
        try {
            RestAssured.baseURI = baseURI;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static RequestSpecification createRequest(boolean log, String contentType, Map<String, String> Token,
                                                      Map<String, String> paramMaps) {
        RequestSpecification request;
        if (log) {
            request = RestAssured.given().log().all();
        } else {
            request = RestAssured.given();
        }
        if (contentType != null) {
            if (contentType.equalsIgnoreCase("JSON")) {
                request.contentType(ContentType.JSON);
            } else if (contentType.equalsIgnoreCase("XML")) {
                request.contentType(ContentType.XML);
            } else if (contentType.equalsIgnoreCase("TEXT")) {
                request.contentType(ContentType.TEXT);
            } else if (contentType.equalsIgnoreCase("multipart")) {
                request.multiPart("image", new File(""));
            }
        }
        if (!(Token == null)) {
            request.headers(Token);
        }

        if (!(paramMaps == null)) {
            request.queryParams(paramMaps);
        }
        return request;
    }


    private static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {
        return executeAPI(httpMethod, request, basePath);
    }

    private static Response executeAPI(String httpMethod, RequestSpecification request, String basePath) {
        Response response = null;
        switch (httpMethod) {
            case "GET":
                response = request.get(basePath);
                break;
            case "POST":
                response = request.post(basePath);
                break;
            case "PUT":
                response = request.put(basePath);
                break;
            case "DELETE":
                response = request.delete(basePath);
                break;

            default:
                System.out.println("Please pass the valid http method");
                break;
        }

        return response;
    }

    public static int getRandomObject(Response response){
        JsonPath jsonPathEvaluator;
        int length;
        jsonPathEvaluator = response.jsonPath();
        length = jsonPathEvaluator.getInt("data.size()");
        return new Random().nextInt(length);
    }

    public static HashMap<String, String> getRandomValues(Response response, String getObject1, String getObject2){
        JsonPath jsonPathEvaluator;
        int length;
        HashMap<String,String> data =new HashMap<>();
        jsonPathEvaluator = response.jsonPath();
        length = jsonPathEvaluator.getInt("data.size()");

        int randomObject = new Random().nextInt(length);
        data.put(getObject1,jsonPathEvaluator.get(""+getObject1+"["+randomObject+"]").toString());
        data.put(getObject2,jsonPathEvaluator.get(""+getObject2+"["+randomObject+"]").toString());

        return data;
    }

    public static HashMap<String, String> getValues(Response response, String getObject1, ArrayList<String> arrayList){
        JsonPath jsonPathEvaluator;
        HashMap<String,String> data =new HashMap<>();
        jsonPathEvaluator = response.jsonPath();
        if(getObject1!=null){
            data.put(getObject1,jsonPathEvaluator.get(""+getObject1+"").toString());
        }else{
            for (String x:arrayList) {
                data.put(x,jsonPathEvaluator.get(""+x+"").toString());
            }
        }
        return data;
    }
public static Response simplePostWithBody(String baseUri,String payload){
        return RestAssured.given().contentType(ContentType.JSON).body(payload).post(baseUri);
}
public static Response simplePutWithBody(String baseUri,String payload){
    return RestAssured.given().contentType(ContentType.JSON).body(payload).put(baseUri);

}
    public static Response simpleGet(String baseUri){
        return RestAssured.given().contentType(ContentType.JSON).get(baseUri);

    }
    public static String getTodayDate(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String formattedDateTime = now.format(formatter);
        System.out.println(formattedDateTime);
        return formattedDateTime;
    }
    public static void addManuallyDateByone(String dateToBeAdded) {
        LocalDate date=LocalDate.parse(dateToBeAdded.substring(0, 10));

        do {
            date = date.plusDays(1);
        } while (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY);

        String newDateString = date.toString() + dateToBeAdded.substring(10);
        System.out.println(newDateString);
        testUtil.update_Properties("ApplyTimeOff", "Time_Off_from_Date", newDateString);
        testUtil.update_Properties("ApplyTimeOff", "Time_Off_to_Date", newDateString);
    }
    public static List<String> timeTrackingAddDate(String startDateOrEndDate, boolean startEndBoolean) {
        String todayDate=getTodayDate();
        //String startDate =applyTTprop.getProperty("start_Time");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime date = LocalDateTime.parse(startDateOrEndDate, formatter);
        LocalDateTime todayDateFormatted = LocalDateTime.parse(todayDate, formatter);
        List<String> listOfDates=new LinkedList<String>();

        if(todayDateFormatted.getMonth()==date.getMonth()){

            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            }
            listOfDates.add(date.format(formatter));
            while (date.plusDays(1).getMonth() == date.getMonth()) {
                // Add a day to the date
                date = date.plusDays(1);

                // Skip weekends
                if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                }
                //check date is in current month
                if(date.getMonth()==todayDateFormatted.getMonth()) {
                    String newDateStr = date.format(formatter);
                    listOfDates.add(newDateStr);
                }else{
                    break;
                }
            }
        }else{
            date=date.with(TemporalAdjusters.firstDayOfNextMonth());
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            }
            String newDateStr = date.format(formatter);
            listOfDates.add(newDateStr);
            if (startEndBoolean == true) {
                testUtil.update_Properties("ApplyTimeTracking", "start_Time", newDateStr);
            } else if (startEndBoolean == false) {
                testUtil.update_Properties("ApplyTimeTracking", "EndTime", newDateStr);
            }

            while (date.plusDays(1).getMonth() == date.getMonth()){
                // Add a day to the date
                date = date.plusDays(1);

                // Skip weekends
                if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                }
                String newMonthDate = date.format(formatter);
                listOfDates.add(newMonthDate);
            }
        }
        return listOfDates;
    }
    public static int getNoOfDaysInCurrentMonth(){
        String todayDate = getTodayDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime todayDateFormatted = LocalDateTime.parse(todayDate, formatter);
        int year = todayDateFormatted.getYear();
        int month = todayDateFormatted.getMonthValue();
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        return daysInMonth;
    }


}

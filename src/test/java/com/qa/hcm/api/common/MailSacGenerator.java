package com.qa.hcm.api.common;


import java.util.Properties;

import com.qa.hcm.api.pojo.CreatePortFolioPojo;

public class MailSacGenerator {

    private static final TestUtil testUtil = new TestUtil();
    static Properties mailProperties = testUtil.init_Properties("MailSac");
    static CreatePortFolioPojo employee = new CreatePortFolioPojo();

    public static String getMailSacMailAddress(String firstName,String lastName){
        return firstName.concat(lastName).concat("1").concat(mailProperties.getProperty("EMAIL_DOMAIN"));
    }


}

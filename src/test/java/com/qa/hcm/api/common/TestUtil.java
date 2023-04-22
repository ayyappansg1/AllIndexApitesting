package com.qa.hcm.api.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TestUtil {

    Properties prop;

    public static String getSerializedJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    public String getExpectedDataFile(String dataFileName) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get("./src/test/resources/"+dataFileName+".json"));
        return new String(jsonData);
    }

    public Properties init_Properties(String fileName){
        prop = new Properties();
        try {
            FileInputStream inputStream=new FileInputStream("./src/test/resources/propertyFiles/"+ fileName+".properties");
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public void update_Properties(String fileName, String key, String value){
        prop = new Properties();
        try {
            FileInputStream inputStream=new FileInputStream("./src/test/resources/propertyFiles/"+ fileName +".properties");
            prop.load(inputStream);
            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream("./src/test/resources/propertyFiles/"+ fileName +".properties");
            prop.setProperty(key,value);
            prop.store(outputStream,"Property file has been updated : "+key+" updated");
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update_listOfProperties(String fileName, HashMap<String, String> hashMap){
        StringBuilder underscore;
        for (String key : hashMap.keySet()) {
            String value = hashMap.get(key);
            System.out.println("The key is :" + key + ", and value is : " + value);
            underscore = new StringBuilder(String.valueOf(Character.toLowerCase(key.charAt(0))));

            for (int i = 1; i < key.length(); i++) {
                underscore.append(Character.isLowerCase(key.charAt(i)) ? String.valueOf(key.charAt(i))
                        : "_" + Character.toLowerCase(key.charAt(i)));
            }
            String key_UpperCase = underscore.toString().toUpperCase().replaceAll("[\\\\.]","");
            new TestUtil().update_Properties(fileName, key_UpperCase, value);
        }
    }



}

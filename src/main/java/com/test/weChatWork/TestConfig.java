package com.test.weChatWork;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import sun.security.krb5.Config;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class TestConfig {
    public String secret = "BF0QWtZw38Kpi-WNpfaaujDsTUsBgyxb_WENxsBdwNw";
    public String agentid = "1000002";
    public String cropid = "ww5eacc7912f8f4b52";
    public String username = null;
    public String password = null;
    public String token = null;
    public String contactsecret = "AmDxGs15g9YlZOcV_Wui3n8hlSrITaBo13MDfz3qAGo";

    private static TestConfig testConfig;
    public static TestConfig getInstance(){
        if (testConfig == null){
            load();
        }else{
//            System.out.println("use exist");
        }
        return testConfig;
    }

    public static String getToken(){
        if(getInstance().token.equals("")){
            //todo: get token
            getInstance().token = given()
                    .queryParam("corpid",getInstance().cropid)
                    .queryParam("corpsecret",getInstance().contactsecret)
                    .when().log().all().get(" https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                    .then().log().all().statusCode(200)
                    .extract().path("access_token");
        }
        System.out.println(getInstance().token);
        return getInstance().token;
    }

//    public static String tokenFresh(){
//        getInstance().token = given()
//                .queryParam("corpid",getInstance().cropid)
//                .queryParam("corpsecret",getInstance().secret)
//                .when().get(" https://qyapi.weixin.qq.com/cgi-bin/gettoken")
//                .then().statusCode(200)
//                .extract().path("access_token");
//        return getInstance().token;
//    }

    public static TestConfig load(String path){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TestConfig testConfig = null;
        try {
//            testConfig = mapper.readValue(new File("D:\\ProjectFile\\weChatWorkTests\\src\\main\\resources\\conf\\wechat.yaml"), TestConfig.class);
              testConfig = mapper.readValue(TestConfig.class.getResourceAsStream(path), TestConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testConfig;
    }

    public static TestConfig load(){
        testConfig = load("/conf/wechat.yaml");
        return testConfig;
    }

    public static String export(){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.writeValueAsString(getInstance());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}

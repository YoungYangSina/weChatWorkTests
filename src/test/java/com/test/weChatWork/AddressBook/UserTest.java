package com.test.weChatWork.AddressBook;

import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user = new User();
    UserData userData = new UserData();

    @Test
    void userCreate() {
        for(int i=0;i<userData.getUserData().size();i++){
            System.out.println("----------这是第"+i+"个测试用例----------"+"\n");
            JSONObject jsonObject = new JSONObject();
            jsonObject = userData.getUserData().getJSONObject(i);
            Response response = user.userCreate(jsonObject);
            response.then().statusCode(200).body("errcode",equalTo(0));
        }
        System.out.println("执行完毕");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data/testFiles.csv")
    void test(){

    }

    @ParameterizedTest
    @ValueSource(strings = {
            "zhangsan",
            "YANGSINA",
            ""
    })
    void userGet(String userid) {
        Response response = user.userGet(userid);
        response.then().statusCode(200);
        response.then().body("errcode",equalTo(0));
    }

    @Test
    void userUpdate() {
    }

    @Test
    void userDelete() {
    }

    @Test
    void userBatchDelete() {
    }

    @Test
    void userSimpleList() {
    }

    @Test
    void userList() {
    }
}
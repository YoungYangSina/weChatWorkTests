package com.test.weChatWork.AddressBook;

import com.test.weChatWork.TestConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class Department {
    public Response create(DepartmentData departmentData){
        return given()
                .contentType(ContentType.JSON)
                .queryParam("access_token",TestConfig.getToken())
                .body(departmentData)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().extract().response();

    }
    public Response list(String id){
        return given()
                .queryParam("access_token",TestConfig.getInstance().getToken())
                .queryParam("id",id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all().extract().response();
    }

    public Response delete(String id){
        return given()
                .queryParam("access_token",TestConfig.getInstance().getToken())
                .queryParam("id",id)
                .when().log().all().get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all().extract().response();

    }
}

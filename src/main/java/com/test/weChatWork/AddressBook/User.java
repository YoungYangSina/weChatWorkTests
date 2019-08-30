package com.test.weChatWork.AddressBook;

import com.test.weChatWork.TestConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class User {

    UserData userData = new UserData();


    //创建成员
    public Response userCreate(JSONObject JsonObject){
//        System.out.println(userData.getJsonUserData());
        String jsonObject = JsonObject.toString();
        return given().log().all()
                .contentType(ContentType.JSON)
                .queryParam("access_token",TestConfig.getInstance().getToken())
                .body(jsonObject)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().all().extract().response();
    }

    //读取成员信息
    public Response userGet(String userid){
        return given().log().all()
                .queryParam("access_token", TestConfig.getInstance().getToken())
                .queryParam("userid",userid)
                .when().log().all().get("https://qyapi.weixin.qq.com/cgi-bin/user/get")
                .then().log().all().extract().response();
    }

    //更新/修改成员
    public void userUpdate(){}

    //删除成员
    public void userDelete(){}

    //批量删除成员
    public void userBatchDelete(){}

    //获取部门成员
    public void userSimpleList(){}

    //获取部门成员详情
    public void userList(){}
}

package com.test.weChatWork.AddressBook;

        import io.qameta.allure.Issue;
        import io.qameta.allure.Story;
        import io.restassured.response.Response;
        import org.junit.jupiter.api.*;
        import org.junit.jupiter.params.ParameterizedTest;
        import org.junit.jupiter.params.provider.CsvFileSource;
        import org.junit.jupiter.params.provider.ValueSource;

        import java.util.ArrayList;
        import java.util.List;

        import static org.hamcrest.MatcherAssert.assertThat;
        import static org.hamcrest.Matchers.equalTo;
        import static org.hamcrest.Matchers.greaterThanOrEqualTo;
        import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest extends BaseDepartmentTestCase {

    @BeforeEach
    void setUp() {

    }

    @ParameterizedTest
    @ValueSource(strings = {
            "鸡仔大班",
            "鸡仔小班",
            "a",
            "1" ,
            "@"})
    void create(String name) {
        departmentData.name = name;
        department.create(departmentData);

        Response response = department.list("");
        response.then().log().all().statusCode(200);
        response.then().body("errcode",equalTo(0));
        response.then().body("department.size()",greaterThanOrEqualTo(0));
        response.then().body("department.find{ it.name == '"+departmentData.name+"'}.name", equalTo(departmentData.name));
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/data/departmentID.csv")
    void list(String id,Integer size) {
//        Department department = new Department();
        Response response = department.list(id);
        System.out.println("id:"+id);
        System.out.println("size:"+size);
        System.out.println(department.list(id));
        response.then().statusCode(200);
        response.then().body("department.size()",greaterThanOrEqualTo(size));
        //asserThat();
    }


    @Test
    void delete() {
        DepartmentData departmentData = new DepartmentData();
        departmentData.name = "删除测试";
        Department department = new Department();
        Integer id = department.list("").path("department.findAll{it.name == '"+departmentData.name+"'}.id[0]");
        System.out.println(id);
        if(id == null){
            id = department.create(departmentData).then().extract().path("id");
            department.list("").then().body("department.findAll{it.name == '"+departmentData.name+"'}.size()",equalTo(1));
            System.out.println(id);
        }
        department.delete(id.toString()).then().body("errcode",equalTo(0));
        department.list("").then().body("department.findAll{it.name == '"+departmentData.name+"'}.size()",equalTo(0));
    }
    @Test
    void deleteWhichChild() {
        departmentData.name = "主部门";
        Integer id = department.list("").path("department.findAll{it.name == '"+departmentData.name+"'}.id[0]");
        if(id == null){
            id = department.create(departmentData).then().extract().path("id");
            departmentData.name = "子部门";
            departmentData.parentid = id;
            department.create(departmentData);
            department.list("").then().body("department.findAll{it.name == '"+departmentData.name+"'}.size()",equalTo(1));
        }
//        departmentData.name = "子部门";
//        departmentData.parentid = id;
//        department.create(departmentData);
//        department.list("").then().body("department.findAll{it.name == '"+departmentData.name+"'}.size()",equalTo(1));
        department.delete(id.toString()).then().body("errcode",equalTo(60006));
        department.list("").then().body("department.findAll{it.name == '"+departmentData.name+"'}.size()",equalTo(1));
    }
}
package com.test.weChatWork.AddressBook;

import com.test.weChatWork.BaseTestCase;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class BaseDepartmentTestCase extends BaseTestCase {

    static DepartmentData departmentData = new DepartmentData();
    static Department department = new Department();

    @BeforeAll
    static void beforeALLBaseDepartmentTestCase(){
        //可单独放在删除部分
        List<String> array = new ArrayList<String>();
        array.add("子部门");
        array.add("主部门");

        for (String name: array){
            departmentData.name = name;
            Integer id = department.list("").path("departmen.find{it.name == '"+departmentData.name+"'}.id[0]");
            if(id != null){
                department.delete(id.toString()).then().body("errcode",equalTo(0));
            }
        }
        System.out.println("data cleaned");
    }
}

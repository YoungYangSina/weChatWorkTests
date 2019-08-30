package com.test.weChatWork.AddressBook;

import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class UserDataTest {

    @Test
    void getUserData() {
        UserData.getUserData();
    }

//    @ParameterizedTest
//    @CsvFileSource(resources = "/data/testFiles.csv")
//    void getJsonUserData(){
//
//        System.out.println(UserData.getJsonUserData());
//    }

}
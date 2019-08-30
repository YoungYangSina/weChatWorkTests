package com.test.weChatWork;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;

class TestConfigTest {

    @Test
    void getToken() {

        assertThat(TestConfig.getToken(),not(equalTo("")));

    }

    @Test
    void load(){
        TestConfig.load();
        assertThat(TestConfig.getInstance().username,equalTo("seveniruby"));
    }

    @Test
    void export(){
        System.out.println(TestConfig.export());
    }

//    @Test
//    void tokenFresh() {
//        assertThat(TestConfig.tokenFresh(),not(equalTo("")));
//    }

}
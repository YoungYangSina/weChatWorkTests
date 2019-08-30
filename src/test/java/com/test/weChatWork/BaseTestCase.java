package com.test.weChatWork;

import org.junit.jupiter.api.BeforeAll;

public class BaseTestCase {
    @BeforeAll
    static void beforeALLBase(){
        System.out.println("user login");
    }
}

package com.kartik.jorunalApp.servce;

import com.kartik.jorunalApp.repository.UserEntryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    UserEntryRepo urepo;
    @Test
    public void ut1(){

        assertEquals(2,2);
        assertNotNull(urepo.findByname("77"));
    }
}

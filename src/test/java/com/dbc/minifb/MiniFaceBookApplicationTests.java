package com.dbc.minifb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


public class MiniFaceBookApplicationTests {

    @Test
    public void contextLoads() {
        String l = "我是";
        StringBuilder sb = new StringBuilder();
        for (byte a : l.getBytes()) {
            sb.append(a >> 4 & 0x0f);
            sb.append(a & 0x0f);
        }
        System.out.println(sb.toString());
    }

}

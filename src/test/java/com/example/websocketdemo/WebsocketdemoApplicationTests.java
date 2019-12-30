package com.example.websocketdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class WebsocketdemoApplicationTests {

    @Test
    void contextLoads() {

        User user = new User("小明");
        log.info("user对象信息为：{}", user);

        SpringBeanManagerUtil.handleAutowireBean("user", user);

        User user1 = SpringBeanManagerUtil.getBean("user", User.class);
        log.info("获取交给spring管理的user：{}", user1);

        SpringBeanManagerUtil.handleRemoveBean("user");

        User user2 = SpringBeanManagerUtil.getBean("user", User.class);
        log.info("获取交给spring管理的user：{}", user2);




    }

}

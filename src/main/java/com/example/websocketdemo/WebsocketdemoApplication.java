package com.example.websocketdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class WebsocketdemoApplication implements ApplicationRunner {

    @Autowired
    private WebSocketClientFactory webSocketClientFactory;

    @Override
    public void run(ApplicationArguments args) {
        // 项目启动的时候打开websocket连接
        webSocketClientFactory.retryOutCallWebSocketClient();
    }

    public static void main(String[] args) {
        SpringApplication.run(WebsocketdemoApplication.class, args);
    }


}

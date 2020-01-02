package com.example.websocketdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OutCallWebSocketController {

    @Autowired
    private WebSocketClientFactory webSocketClientFactory;

    @GetMapping("/sendCall")
    public void sendCall() {
        String heartBeatMsg = "{\"event\":\"heartbeat\",\"sid\":\"1ae4e3167b3b49c7bfc6b79a74f229691562914214595\"}";
        webSocketClientFactory.sendMsg(webSocketClientFactory.getOutCallWebSocketClientHolder(), heartBeatMsg);
    }

}
package com.example.websocketdemo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
@Slf4j
@Data
public class WebSocketClientFactory {

    public static final String outCallWebSockertUrl = "ws://10.1.3.137:356";

    private WebSocketClient outCallWebSocketClientHolder;


    /**
     * 创建websocket对象
     *
     * @return
     * @throws URISyntaxException
     */
    private WebSocketClient createNewWebSocketClient() throws URISyntaxException {
        WebSocketClient webSocketClient = new WebSocketClient(new URI(outCallWebSockertUrl)) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
            }

            @Override
            public void onMessage(String msg) {
                log.info("接收信息为：{}", msg);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                log.info("关闭连接");
                retryOutCallWebSocketClient();
            }

            @Override
            public void onError(Exception e) {
                log.error("连接异常");
                retryOutCallWebSocketClient();
            }
        };
        webSocketClient.connect();
        return webSocketClient;
    }


    /**
     * 连接失败的时候打开新链接
     */
    public WebSocketClient retryOutCallWebSocketClient() {
        try {
            WebSocketClient webSocketClient = this.createNewWebSocketClient();

            while (!webSocketClient.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
                log.debug("正在连接中,请稍后........");
            }
            String sendOpenJsonStr = "{\"event\":\"connect\",\"sid\":\"1ae4e3167b3b49c7bfc6b79a74f229691562914214595\",\"token\":\"df59eba89ce949ac866a2312063e10b6\"}";
            webSocketClient.send(sendOpenJsonStr);

            // 每次创建新的就放进去
            this.setOutCallWebSocketClientHolder(webSocketClient);
            return webSocketClient;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }


    public void sendMsg(WebSocketClient webSocketClient, String message) {
        while (!webSocketClient.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
            log.debug("正在建立通道，请稍等");
        }
        webSocketClient.send(message);
    }


}
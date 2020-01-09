package cn.cosmos.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket的处理类
 *
 * @author CosmosRay
 * @date 2020年1月9日15:39:10
 */
@Slf4j
@ServerEndpoint(value = "/websocket",configurator = WebSocketConfig.class)
@Component
public class WebSocketServer{
    /**
     * 保存用户链接
     */
    private static ConcurrentHashMap<String, Session> users = new ConcurrentHashMap<>();

    /**
     * 连接 就绪时
     *
     * @param session
     */
    @OnOpen
    public void afterConnectionEstablished(Session session){
        System.out.println("websocket连接就绪");
        users.put(session.getId(), session);
    }

    /**
     * 第一次连接返回的数据
     *
     * @param session session
     */
    @OnMessage
    public void handleMessage(String message,Session session) {
        try {
            session.getBasicRemote().sendText("第一次调用成功");
            System.out.println("首次数据推送完成");
        }catch (Exception e){
            System.err.println("首次数据推送出错:{}"+e);
        }
    }

    @OnError
    public void handleTransportError(Session session, Throwable throwable) throws Exception {

    }

    @OnClose
    public void afterConnectionClosed(Session session){
        users.remove(session.getId());
    }

    /**
     * 给所有会话推数据
     * @param message 推送数据
     */
    public static void sendMessage(String message) {
        try {
            for (Map.Entry<String, Session> entry : users.entrySet()) {
                Session value = entry.getValue();
                value.getBasicRemote().sendText("调用成功："+message);
            }
        } catch (Exception e) {
            System.err.println("给前端推送数据出错:"+ e);
        }

    }
}

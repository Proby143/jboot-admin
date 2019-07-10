package io.jboot.admin.controller.teacity;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * describe:
 *
 * @author 白野
 * @date 2019\7\10 0010
 */
@ServerEndpoint("/websocket")
public class WebSocket {
    //当前在线连接数
    private static int onlineCount = 0;

    //存储每个客户端的对象
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();

    //客户端session
    private Session session;

    @OnOpen
    public void onOpen(Session session) {

        this.session = session;
        webSocketSet.add(this);
        onlineCount++;
        onMessage("有新连接加入！当前在线人数为" + onlineCount, this.session);
    }

    @OnClose
    public void close(){

        webSocketSet.remove(this);
        onlineCount--;
        onMessage("有一连接关闭！当前在线人数为" + onlineCount, this.session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {

        for(WebSocket item: webSocketSet){
            try {
                //当对象处于开启状态下执行以下
                item.session.getBasicRemote().sendText(message);
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}

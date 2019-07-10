package io.jboot.admin.controller.teacity;

import io.jboot.admin.base.web.base.BaseController;
import io.jboot.web.controller.annotation.RequestMapping;

import javax.websocket.server.ServerEndpoint;

/**
 * describe:
 *
 * @author 白野
 * @date 2019\7\10 0010
 */
@RequestMapping("/teacity/chat")
public class ChatController extends BaseController {

    public void index(){
        render("/template/teacity/chat/main.html");
    }

}

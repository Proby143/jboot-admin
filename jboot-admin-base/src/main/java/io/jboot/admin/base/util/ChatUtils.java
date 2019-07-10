package io.jboot.admin.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.plugin.activerecord.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatUtils {

    public static Page<Map<String,String>> getChatList(JSONArray jsonArray,String page){
        Integer pageNum = Integer.parseInt(page);
        System.out.println(jsonArray);
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> chat = null;
        if(jsonArray.size()>30){
            for (int i =jsonArray.size()-30;i<jsonArray.size();i++){
                Msg msg = JSON.parseObject((String) jsonArray.get(i),Msg.class);
                chat = new HashMap<>();
                chat.put("type",msg.getType());
                chat.put("message",msg.getMsg());
                list.add(chat);
            }
        }else{
            for (int i = 0; i < jsonArray.size(); i++) {
                Msg msg = JSON.parseObject((String) jsonArray.get(i),Msg.class);
                chat = new HashMap<>();
                chat.put("type",msg.getType());
                chat.put("message",msg.getMsg());
                list.add(chat);
            }
        }

        Page<Map<String,String>> msgPage = new Page<Map<String,String>>(list,pageNum,30, MathUtil.getPage(jsonArray.size(),30),jsonArray.size());
        System.out.println("共取出:"+list.size()+"条记录");
        return msgPage;
    }
}

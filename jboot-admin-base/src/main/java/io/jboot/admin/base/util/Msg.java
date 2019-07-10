package io.jboot.admin.base.util;

import java.io.Serializable;

public class Msg implements Serializable {


    private static final long serialVersionUID = 5737285991645986303L;

    private String type;

    private String msg;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

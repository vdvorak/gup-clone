package com.journaldev.spring;

import java.io.Serializable;

public class CreatedObjResp implements Serializable {

    private static final long serialVersionUID = -7788619177798333712L;

    private String id;

    public CreatedObjResp() {}

    public CreatedObjResp(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

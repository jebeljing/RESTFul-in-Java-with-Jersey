package com.pluralsight.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Jingshan on 9/22/2017.
 */
@XmlRootElement
public class User {

    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

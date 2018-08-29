package com.example.ericgrehan.myrealartapplication;

import java.io.Serializable;

public class ArtPlace implements Serializable {
    private String id;
    private String name;
    private String location;
    private String description;
    private String imgurl;

    public ArtPlace(){} //Need empty constructor to receive info from the DB

    public ArtPlace(String name, String location, String description, String imgurl) {
        this.setId(id);
        this.setName(name);
        this.setLocation(location);
        this.setDescription(description);
        this.setImgurl(imgurl);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}

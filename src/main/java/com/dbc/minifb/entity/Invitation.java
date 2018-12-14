package com.dbc.minifb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Knox
 * @Date: 2018/12/1 11:12 PM
 * @Description: You Know
 * @Version 1.0
 */
@NodeEntity
public class Invitation {
    @GeneratedValue
    @Id
    private Long id;

    @Property
    private Date time;

    @Property
    private String Content;

    @Property
    private List<String> imagesUrl = new ArrayList<>();

    @Relationship(type = "POST_BY")
    private User postBy;

    public Invitation() {
    }

    public Invitation(Date time, String content, User postBy) {
        this.time = time;
        Content = content;
        this.postBy = postBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public User getPostBy() {
        return postBy;
    }

    public void setPostBy(User postBy) {
        this.postBy = postBy;
    }

    @Override
    public String toString() {
        return "Invitation{" +
                "id=" + id +
                ", time=" + time +
                ", Content='" + Content + '\'' +
                ", imagesUrl=" + imagesUrl +
                ", postBy=" + postBy +
                '}';
    }
}

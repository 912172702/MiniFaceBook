package com.dbc.minifb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;

/**
 * @Author: Knox
 * @Date: 2018/12/1 11:13 PM
 * @Description: You Know
 * @Version 1.0
 */
@NodeEntity
public class Comment {
    @GeneratedValue
    @Id
    private Long id;

    @Property
    private String content;

    @Property
    private Date time;

    @Relationship(type = "WRITE_BY")
    private User writeBy;
    @JsonIgnore
    @Relationship(type = "BELONG_TO")
    private Invitation belongTo;

    public Comment() {
    }

    public Comment(String content, Date time, User writeBy, Invitation belongTo) {
        this.content = content;
        this.time = time;
        this.writeBy = writeBy;
        this.belongTo = belongTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getWriteBy() {
        return writeBy;
    }

    public void setWriteBy(User writeBy) {
        this.writeBy = writeBy;
    }

    public Invitation getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(Invitation belongTo) {
        this.belongTo = belongTo;
    }
}

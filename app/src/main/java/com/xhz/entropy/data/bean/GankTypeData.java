package com.xhz.entropy.data.bean;

import java.util.Date;
import java.util.List;

/**
 * Created by xh.zeng on 2017/1/2.
 */

public class GankTypeData {

    /**
     * _id : 5832b8be421aa929b66fb0ff
     * createdAt : 2016-11-21T17:05:02.20Z
     * desc : android温度计
     * images : ["http://img.gank.io/d0aa1630-6d65-4126-aa73-7ab340d18dbf"]
     * publishedAt : 2016-11-24T11:40:53.615Z
     * source : chrome
     * type : Android
     * url : https://github.com/kofigyan/Thermometer
     * used : true
     * who : 有时放纵
     */

    private String _id;
    private Date createdAt;
    private String desc;
    private Date publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    @Override
    public String toString() {
        return "GankTypeData{" +
                "_id=" + _id +
                ", createdAt='" + createdAt +
                ", desc=" + desc +
                ", publishedAt=" + publishedAt +
                ", source=" + source +
                ", type=" + type +
                ", url=" + url +
                ", used=" + used +
                ", who=" + who +
                ", images=" + images +
                "}\n";
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}


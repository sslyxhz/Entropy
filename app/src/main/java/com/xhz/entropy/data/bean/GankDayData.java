package com.xhz.entropy.data.bean;

import java.util.Date;
import java.util.List;

/**
 * Created by xh.zeng on 2017/1/2.
 */

public class GankDayData {

    /**
     * _id : 5836a7fc421aa91cb7afe7e0
     * createdAt : 2016-11-24T16:42:36.919Z
     * desc : 支持https的ijkplayer播放器
     * images : ["http://img.gank.io/22aa7a50-de1f-4697-8eb8-7bcc247cce58"]
     * publishedAt : 2016-11-25T11:29:49.832Z
     * source : web
     * type : Android
     * url : https://github.com/l123456789jy/ijkplayer
     * used : true
     * who : Lazy
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
        return "GankDayData{" +
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
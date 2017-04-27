package com.cyonakute.models;

import io.vertx.core.json.JsonObject;
import java.util.Date;

/**
 * Created by Opeyemi.Akinnawo on 4/25/2017.
 */
public class Announcement {

    private int annId;
    private String title;
    private String message;
    private String linkUrl;
    private JsonObject createdAt;

    public Announcement(int annId, String title, String message, String linkUrl, JsonObject createdAt) {
        this.setAnnId(annId);
        this.setTitle(title);
        this.setMessage(message);
        this.setLinkUrl(linkUrl);
        this.setCreatedAt(createdAt);
    }

    public Announcement() {
    }

    public Announcement(JsonObject json) throws Exception {
        fromJson(json, this);
    }

    public JsonObject getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(JsonObject createdAt) {
        this.createdAt = createdAt;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getAnnId() {
        return annId;
    }

    public void setAnnId(int annId) {
        this.annId = annId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void fromJson(JsonObject json, Announcement obj) throws Exception {
        try {
            if (json.getValue("title") instanceof String) {
                obj.setTitle((String)json.getValue("title"));
            }
            if (json.getValue("message") instanceof String) {
                obj.setMessage((String)json.getValue("message"));
            }
            if (json.getValue("linkUrl") instanceof String) {
                obj.setLinkUrl((String)json.getValue("linkUrl"));
            }
        }
        catch (Exception exception) {
            throw new Exception("Unable to parse json object as announcement");
        }
    }
}

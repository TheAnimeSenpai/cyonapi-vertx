package com.cyonakute.models;

/**
 * Created by Opeyemi.Akinnawo on 4/25/2017.
 */
public class Announcement {

    private int annId;
    private String title;
    private String message;
    private String linkUrl;
    private String createdAt;

    public Announcement(int annId, String title, String message, String linkUrl, String createdAt) {
        this.setAnnId(annId);
        this.setTitle(title);
        this.setMessage(message);
        this.setLinkUrl(linkUrl);
        this.setCreatedAt(createdAt);
    }

    public Announcement() {
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
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
}

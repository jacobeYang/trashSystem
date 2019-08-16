package com.jacob.trash.domain;

public class SiteTrash {
    private String id;

    private String siteId;

    private String trashId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId == null ? null : siteId.trim();
    }

    public String getTrashId() {
        return trashId;
    }

    public void setTrashId(String trashId) {
        this.trashId = trashId == null ? null : trashId.trim();
    }
}
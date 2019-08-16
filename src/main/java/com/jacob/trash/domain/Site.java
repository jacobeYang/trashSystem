package com.jacob.trash.domain;

public class Site {
    private String id;

    private String name;

    private Integer type;

    private Integer trashNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTrashNumber() {
        return trashNumber;
    }

    public void setTrashNumber(Integer trashNumber) {
        this.trashNumber = trashNumber;
    }
}
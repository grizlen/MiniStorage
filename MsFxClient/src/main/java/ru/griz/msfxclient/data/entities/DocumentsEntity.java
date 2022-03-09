package ru.griz.msfxclient.data.entities;

public class DocumentsEntity extends Entity {
    private String type;

    public DocumentsEntity() {
        this(null, null);
    }

    public DocumentsEntity(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

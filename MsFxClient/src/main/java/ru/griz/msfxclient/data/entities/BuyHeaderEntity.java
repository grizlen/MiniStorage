package ru.griz.msfxclient.data.entities;

import java.util.Date;

public class BuyHeaderEntity extends Entity {
    private Date date;

    public BuyHeaderEntity() {
        this(null, null);
    }

    public BuyHeaderEntity(Long id, Date date) {
        this.id = id;
        this.date = date == null ? new Date() : date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

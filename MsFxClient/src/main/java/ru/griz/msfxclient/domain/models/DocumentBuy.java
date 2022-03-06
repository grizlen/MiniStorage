package ru.griz.msfxclient.domain.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DocumentBuy {
    private Long id;
    private LocalDateTime date;

    public DocumentBuy() {
        date = LocalDateTime.now();
    }

    public DocumentBuy(Long id, LocalDateTime date) {
        this.id = id;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Buy id = " + id + ", date = " + date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }
}

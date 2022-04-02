package ru.griz.msfxclient.domain.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DocHeader {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public static String formatDate(Date date) {
        return formatter.format(date);
    }

    public static Date parseDate(String date) {
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    protected Long id;
    protected Date date;

    public DocHeader() {
        date = new Date();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String date) {
        this.date = parseDate(date);
    }

    public Date getDate() {
        return date;
    }

    public String getDateStr() {
        return formatDate(date);
    }
}

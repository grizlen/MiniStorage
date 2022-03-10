package ru.griz.ms.server.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "doc_returns")
@Getter
@Setter
public class ReturnHeader {
    @Id
    Long id;
    Date date;

    public ReturnHeader() {
        date = new Date();
    }
}

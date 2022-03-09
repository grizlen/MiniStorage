package ru.griz.ms.server.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "doc_buys")
@Getter
@Setter
public class BuyHeader {
    @Id
    Long id;
    Date date;

    public BuyHeader() {
        date = new Date();
    }
}

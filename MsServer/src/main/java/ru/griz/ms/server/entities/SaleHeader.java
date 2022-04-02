package ru.griz.ms.server.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "doc_sales")
@Getter
@Setter
public class SaleHeader {
    @Id
    Long id;
    Date date;
    Long SubDepartmentId;

    public SaleHeader() {
        date = new Date();
    }
}

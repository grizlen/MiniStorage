package ru.griz.ms.server.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "doc_buy_items")
@Getter
@Setter
@NoArgsConstructor
public class BuyItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long docId;
    Long productId;
    Integer count;
}

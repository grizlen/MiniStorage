package ru.griz.ms.server.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sub_department")
@Getter
@Setter
@NoArgsConstructor
public class SubDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long department;
    private String name;
}

package io.samancore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "tm1")
public class TmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_state")
    @Column(nullable = false)
    private Long id;

    @Column
    private Long edad;

    @Column(name= "no_fumador")
    private BigDecimal noFumador;

    @Column(name= "fumador")
    private BigDecimal fumador;

    @Column(length  = 8)
    private String genero;
}

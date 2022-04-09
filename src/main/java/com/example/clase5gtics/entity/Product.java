package com.example.clase5gtics.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    private int id;
    @Column(nullable = false)
    private String productname;
    @ManyToOne
    @JoinColumn(name = "supplierid")
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;
    private String quantityperunit;
    private BigDecimal unitprice;
    private int unitsinstock;
    private int unitsonorder;
    private int reorderlevel;
    @Column(nullable = false)
    private boolean discontinued;

}

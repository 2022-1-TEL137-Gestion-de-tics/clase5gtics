package com.example.clase5gtics.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @NotBlank
    @Size(max = 40, message = "El nombre no puede ser mayor a 40 caracteres")
    private String productname;
    @ManyToOne
    @JoinColumn(name = "supplierid")
    private Supplier supplier = new Supplier();
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category = new Category();
    private String quantityperunit;
    @Positive
    @Digits(integer = 10, fraction = 4)
    private BigDecimal unitprice;
    @Digits(integer = 10, fraction = 0)
    @Max(value = 32767)
    @Min(value = 0)
    private int unitsinstock;
    @Digits(integer = 10, fraction = 0)
    @Max(value = 32767)
    @Min(value = 0)
    private int unitsonorder;
    private int reorderlevel;
    @Column(nullable = false)
    private boolean discontinued;

}

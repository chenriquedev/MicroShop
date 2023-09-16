package com.henrique.java.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float preco;
    private String descricao;
    @Column(name = "product_indentifier")
    private String productIdentifier;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

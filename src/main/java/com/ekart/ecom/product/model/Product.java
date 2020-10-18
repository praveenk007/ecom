package com.ekart.ecom.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String displayName;

    private String description;

    private Double mrp;

    private Double buyPrice;

    private Long qtyInStock;

    private String imageUrl;

    private String color;

    private Boolean isActive;

    @ManyToOne
    @JoinTable(name = "product_brand", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "brand_id")})
    private Brand brand;
}

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

    @Column(name = "name")
    private String name;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "description")
    private String description;

    @Column(name = "mrp")
    private Double mrp;

    @Column(name = "buy_price")
    private Double buyPrice;

    @Column(name = "qty_in_stock")
    private Long qtyInStock;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "color")
    private String color;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinTable(name = "product_brand", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "brand_id")})
    private Brand brand;

    @OneToOne
    @JoinTable(name = "product_vendor", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "vendor_id")})
    private Vendor vendor;
}

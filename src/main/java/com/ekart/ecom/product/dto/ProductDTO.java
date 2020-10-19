package com.ekart.ecom.product.dto;

import com.ekart.ecom.product.model.Brand;
import com.ekart.ecom.product.model.Product;
import com.ekart.ecom.product.model.ProductSpecification;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private Long id;

    private String name;

    private Long brandId;

    private String displayName;

    private String description;

    private String color;

    private String model;

    private Long categoryId;

    private Boolean isActive;

    private String imageUrl;

    private Long qtyInStock;

    private Double buyPrice;

    private Double mrp;

    private Long vendorId;

    public Product toModel() {
        ProductSpecification productSpecification = ProductSpecification.builder().color(this.getColor()).build();
        Product product = Product.builder()
                .brand(Brand.builder().id(this.getBrandId()).build())
                .description(this.getDescription())
                .displayName(this.getDisplayName())
                .name(this.getName())
                .isActive(this.getIsActive())
                .imageUrl(this.getImageUrl())
                .mrp(this.getMrp())
                .qtyInStock(this.getQtyInStock())
                .buyPrice(this.getBuyPrice())
                .productSpecification(productSpecification)
                .build();
        if(this.getId() != null) {
            product.setId(this.getId());
        }
        return product;
    }

    public static ProductDTO toDTO(final Product product) {
        if(product == null) {
            return null;
        }
        return ProductDTO.builder()
                .brandId(product.getBrand().getId())
                .buyPrice(product.getBuyPrice())
                .color(product.getProductSpecification().getColor())
                .description(product.getDescription())
                .displayName(product.getDisplayName())
                .id(product.getId())
                .isActive(product.getIsActive())
                .imageUrl(product.getImageUrl())
                .mrp(product.getMrp())
                .qtyInStock(product.getQtyInStock())
                .build();
    }
}

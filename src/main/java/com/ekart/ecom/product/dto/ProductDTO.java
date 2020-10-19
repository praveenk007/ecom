package com.ekart.ecom.product.dto;

import com.ekart.ecom.product.model.Brand;
import com.ekart.ecom.product.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("brand_id")
    private Long brandId;

    @JsonProperty("display_name")
    private String displayName;

    private String description;

    private String color;

    private String model;

    private Long categoryId;

    private Boolean isActive;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("qty_in_stock")
    private Long qtyInStock;

    @JsonProperty("buy_price")
    private Double buyPrice;

    private Double mrp;

    @JsonProperty("vendor_id")
    private Long vendorId;

    public Product toModel() {
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
                .color(this.getColor())
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
                .color(product.getColor())
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

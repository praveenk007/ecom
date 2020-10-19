package com.ekart.ecom.product.dto;

import com.ekart.ecom.product.model.Brand;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandDTO {

    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("active")
    private Boolean isActive;

    public Brand toModel() {
        return Brand.builder()
                .name(this.getName())
                .description(this.getDescription())
                .displayName(this.getDisplayName())
                .isActive(this.getIsActive())
                .build();
    }

    public static BrandDTO toDTO(@NotNull final Brand brand) {
        return BrandDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .displayName(brand.getDisplayName())
                .build();
    }
}

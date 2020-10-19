package com.ekart.ecom.product.controller;

import com.ekart.ecom.product.dto.BrandDTO;
import com.ekart.ecom.product.dto.Response;
import com.ekart.ecom.product.model.Brand;
import com.ekart.ecom.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kamathp
 * @version 0.0.1
 */
@RestController
@RequestMapping("/api/brand/0.1")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/create")
    public ResponseEntity<Response<BrandDTO>> create(@RequestBody final BrandDTO brandDTO) {
        final Brand brand = brandService.create(brandDTO.toModel());
        final Response<BrandDTO> response = new Response<>();
        response.setData(BrandDTO.toDTO(brand));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

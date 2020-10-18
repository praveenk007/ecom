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
@Table(name = "vendors")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "owner")
    private String owner;

    @OneToOne
    @JoinTable(name = "vendors_address", joinColumns = {@JoinColumn(name = "vendor_id")}, inverseJoinColumns = {@JoinColumn(name = "address_id")})
    private Address address;

    @Column(name = "is_active")
    private Boolean isActive;
}

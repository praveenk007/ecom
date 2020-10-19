package com.ekart.ecom.product.model;

import com.ekart.ecom.product.enums.PaymentMode;
import com.ekart.ecom.product.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author kamathp
 * @version 0.0.1
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private PaymentStatus status;

    private Long amount;

    /**
     * You can pay some part from your wallet and remaining from other pay mode
     */
    @OneToMany
    @JoinTable(name = "payments_payment_mode", joinColumns = {@JoinColumn(name = "payments_id")}, inverseJoinColumns = {@JoinColumn(name = "payment_mode_id")})
    private List<PaymentMode> paymentMode;

    private Date paymentDate;
}

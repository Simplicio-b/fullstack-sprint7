package br.com.rchlo.store.dto;

import br.com.rchlo.store.domain.Card;
import br.com.rchlo.store.domain.Payment;
import br.com.rchlo.store.domain.PaymentStatus;

import javax.persistence.*;
import java.math.BigDecimal;

public class PaymentDto {

    private Long id;
    private BigDecimal value;
    private PaymentStatus status;

    public PaymentDto(Payment payment) {
        this.id = payment.getId();
        this.value = payment.getValue();
        this.status = payment.getStatus();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}

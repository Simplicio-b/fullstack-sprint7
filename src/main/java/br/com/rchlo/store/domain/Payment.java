package br.com.rchlo.store.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Embedded
    @AttributeOverride(name = "clientName", column = @Column(name = "card_client_name"))
    @AttributeOverride(name = "number", column = @Column(name = "card_number"))
    @AttributeOverride(name = "expiration", column = @Column(name = "card_expiration"))
    @AttributeOverride(name = "verificationCode", column = @Column(name = "card_verification_code"))
    private Card card;

    public Payment() {

    }

    public Payment(BigDecimal value, Card card) {
        this.value = value;
        this.card = card;
        this.status = PaymentStatus.CREATED;
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

    public Card getCard() {
        return card;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public void setCard(Card card) {
        this.card = card;
    }

}

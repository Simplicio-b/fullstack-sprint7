package br.com.rchlo.store.controller.form;

import br.com.rchlo.store.domain.Card;
import br.com.rchlo.store.domain.Payment;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PaymentForm {

    @NotNull @Positive
    private BigDecimal value;
    @NotNull @NotBlank @Length(max=100)
    private String clientName;
    @NotNull @NotBlank
    private String cardNumber;
    @NotNull
    private String expiration;
    @NotNull
    private String verificationCode;

    public PaymentForm(BigDecimal value, String clientName, String cardNumber, String expiration, String verificationCode) {
        this.value = value;
        this.clientName = clientName;
        this.cardNumber = cardNumber;
        this.expiration = expiration;
        this.verificationCode = verificationCode;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getClientName() {
        return clientName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Payment parseToPayment() {
        return new Payment(this.value, new Card(this.clientName, this.cardNumber, this.expiration, this.verificationCode));
    }
}

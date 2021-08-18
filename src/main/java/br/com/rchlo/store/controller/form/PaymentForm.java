package br.com.rchlo.store.controller.form;

import br.com.rchlo.store.domain.Card;
import br.com.rchlo.store.domain.Payment;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.YearMonth;

public class PaymentForm {

    @NotNull @Positive
    private BigDecimal value;
    @NotNull @NotBlank @Size(max=100)
    private String clientName;
    @NotNull @Pattern(regexp = "\\d{16}")
    private String cardNumber;
    @NotNull @Future
    private YearMonth expiration;
    @NotNull @Pattern(regexp = "\\d{3}")
    private String verificationCode;

    public PaymentForm(BigDecimal value, String clientName, String cardNumber, YearMonth expiration, String verificationCode) {
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
        return expiration.toString();
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

    public void setExpiration(YearMonth expiration) {
        this.expiration = expiration;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Payment parseToPayment() {
        return new Payment(this.value, new Card(this.clientName, this.cardNumber, this.expiration.toString(), this.verificationCode));
    }
}

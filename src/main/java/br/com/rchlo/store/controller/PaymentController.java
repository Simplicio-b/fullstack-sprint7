package br.com.rchlo.store.controller;

import br.com.rchlo.store.domain.Payment;
import br.com.rchlo.store.dto.PaymentDto;
import br.com.rchlo.store.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository repository;

    @GetMapping
    public List<PaymentDto> getPaymentsList(){
        List<Payment> payments = repository.findAll();
        return payments.stream().map(PaymentDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> payments(@PathVariable Long id) {
        Optional<Payment> payment = repository.findById(id);
        if (payment.isPresent()) {
            return ResponseEntity.ok(new PaymentDto(payment.get()));
        }

        return ResponseEntity.notFound().build();
    }


}

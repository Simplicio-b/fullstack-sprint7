package br.com.rchlo.store.controller;

import br.com.rchlo.store.controller.form.PaymentForm;
import br.com.rchlo.store.domain.Payment;
import br.com.rchlo.store.dto.PaymentDto;
import br.com.rchlo.store.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping
    @Transactional
    public ResponseEntity<PaymentDto> newPayment(@RequestBody @Valid PaymentForm form, UriComponentsBuilder uriBuilder) {
        Payment payment = form.parseToPayment();
        repository.save(payment);
        URI uri = uriBuilder.path("payments/{id}").buildAndExpand(payment.getId()).toUri();
        return ResponseEntity.created(uri).body(new PaymentDto(payment));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PaymentDto> update(@PathVariable Long id){
        Optional<Payment> payment = repository.findById(id);

        try {
            payment.get().confirmPayment();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new PaymentDto(payment.get()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<PaymentDto> delete(@PathVariable Long id){
        Optional<Payment> payment = repository.findById(id);

        try {
            payment.get().cancelPayment();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new PaymentDto(payment.get()));
    }


}

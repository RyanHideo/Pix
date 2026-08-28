package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "transaction_code", nullable = false, unique = true)
    private String transactionCode;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "account_sender_id", nullable = false)
    private Account accountSender;

    @NotNull
    @Column(name = "pix_key_used", nullable = false)
    private String pixKey;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "account_receiver_id", nullable = false)
    private Account accountReceiver;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @Column(name = "value", nullable = false)
    private BigDecimal value;


    protected Transfer() {
    }

    public Transfer(Account accountSender, String pixKey, Account accountReceiver, BigDecimal value) {
        this.accountSender = accountSender;
        this.pixKey = pixKey;
        this.accountReceiver = accountReceiver;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public Account getAccountSender() {
        return accountSender;
    }

    public String getPixKey() {
        return pixKey;
    }

    public Account getAccountReceiver() {
        return accountReceiver;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getValue() {
        return value;
    }


}

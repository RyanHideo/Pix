package org.example.pix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class TransferRequestDTO{

    @NotNull
    @Positive
    private Long senderID;

    @NotNull
    @NotEmpty
    @NotBlank
    private String pixKey;

    @NotNull
    @Positive
    private BigDecimal value;

    public TransferRequestDTO() {
    }

    public TransferRequestDTO(Long senderID, String pixKey, BigDecimal value) {
        this.senderID = senderID;
        this.pixKey = pixKey;
        this.value = value;
    }

    public Long getSenderID() {
        return senderID;
    }

    public void setSenderID(Long senderID) {
        this.senderID = senderID;
    }

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}

package org.example.pix.controller;

import jakarta.validation.Valid;
import org.example.pix.dto.TransferRequestDTO;
import org.example.pix.model.Transfer;
import org.example.pix.service.TransferService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService){
        this.transferService = transferService;
    }


    @PostMapping("/transfers")
    public Transfer transfer(@Valid @RequestBody TransferRequestDTO transferRequestDTO){

        return transferService.makeTransfer(
                transferRequestDTO.getSenderId(),
                transferRequestDTO.getPixKey(),
                transferRequestDTO.getValue()
        );
    }
}

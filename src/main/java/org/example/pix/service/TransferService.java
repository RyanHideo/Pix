package org.example.pix.service;

import jakarta.transaction.Transactional;
import org.example.pix.model.*;
import org.example.pix.repository.AccountRepository;
import org.example.pix.repository.PixKeyRepository;
import org.example.pix.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {

    private final PixKeyRepository pixKeyRepository;
    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;

    public TransferService(PixKeyRepository pixKeyRepository, TransferRepository transferRepository, AccountRepository accountRepository) {
        this.pixKeyRepository = pixKeyRepository;
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
    }

    private void validateValue(BigDecimal value){

        if (value == null){
             throw new IllegalArgumentException("Insira um valor");
        }
        if(value.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
    }

    private PixKey validatePixKey(String pixKey) {
        var keyData = pixKeyRepository.findByPixKey(pixKey)
                .orElseThrow(() -> new IllegalArgumentException("Chave Pix não encontrada."));


        if (keyData.getStatusKeyPix() != StatusKeyPix.ATIVA) {
            throw new IllegalArgumentException("A chave Pix informada não está ativa.");
        }
        return keyData;
    }

    private Account validateAccount(Account account){

        //Verificar se account esta nulo
        if (account == null){
            throw new IllegalArgumentException("Conta nao informada");
        }

        //Verificar status de account
        if (account.getStatusAccount() != StatusAccount.ATIVO){
            throw new IllegalArgumentException("A conta informada não está ativa.");
        }
    return account;
    }

    private void validateDifferentAccounts(Account sender, Account receiver){
        // Validação de segurança para evitar NullPointerException
        if (sender == null || receiver == null || sender.getId() == null || receiver.getId() == null) {
            throw new IllegalArgumentException("As contas e seus IDs não podem ser nulos");
        }

        // Compara os Longs de forma segura
        if (sender.getId().equals(receiver.getId())){
            throw new IllegalArgumentException("As contas devem ser diferentes");
        }
    }

    private void validateBalance(Account sender, BigDecimal value){
        if (sender.getBalance().compareTo(value) < 0){
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }

    @Transactional
    public Transfer makeTransfer(Long senderId, String pix, BigDecimal value){
        validateValue(value);

        var sender = accountRepository.findById(senderId)
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Conta remetente não encontrada"
                        ));

        Account senderValidada = validateAccount(sender);

        PixKey chaveValidada = validatePixKey(pix);

        Account receiver = chaveValidada.getAccount();

        receiver = validateAccount(receiver);

        validateDifferentAccounts(senderValidada, receiver);

        validateBalance(senderValidada,value);

        senderValidada.debitBalance(value);

        receiver.creditBalance(value);

        Transfer transfer = new Transfer(senderValidada, pix, receiver, value);

        Transfer savedTransfer = transferRepository.save(transfer);

        return savedTransfer;
    }
}

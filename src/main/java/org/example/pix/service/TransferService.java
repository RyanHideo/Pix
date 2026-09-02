package org.example.pix.service;

import org.example.pix.model.Account;
import org.example.pix.model.PixKey;
import org.example.pix.model.StatusAccount;
import org.example.pix.model.StatusKeyPix;
import org.example.pix.repository.PixKeyRepository;
import org.example.pix.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {

    private final PixKeyRepository pixKeyRepository;
    private final TransferRepository transferRepository;

    public TransferService(PixKeyRepository pixKeyRepository, TransferRepository transferRepository) {
        this.pixKeyRepository = pixKeyRepository;
        this.transferRepository = transferRepository;
    }

    public void validateValue(BigDecimal value){

        if (value == null){
             throw new IllegalArgumentException("Insira um valor");
        }
        if(value.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
    }

    public PixKey validatePixKey(String pixKey) {
        var keyData = pixKeyRepository.findByPixKey(pixKey)
                .orElseThrow(() -> new IllegalArgumentException("Chave Pix não encontrada."));


        if (keyData.getStatusKeyPix() != StatusKeyPix.ATIVA) {
            throw new IllegalArgumentException("A chave Pix informada não está ativa.");
        }
        return keyData;
    }

    public Account validateAccount(Account account){

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


}

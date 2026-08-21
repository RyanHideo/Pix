package model;

import jakarta.persistence.*;

@Entity
public class PixKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    private Account account;

    @Column(name = "key_pix", nullable = false, unique = true, length = 120)
    private String keyPix;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_key_pix", nullable = false)
    private StatusKeyPix statusKeyPix = StatusKeyPix.ATIVA;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_key_pix", nullable = false)
    private TypeKeyPix typeKeyPix;

    protected PixKey() {
    }

    public PixKey(Account account, String keyPix, TypeKeyPix typeKeyPix) {
        this.account = account;
        this.keyPix = keyPix;
        this.typeKeyPix = typeKeyPix;
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public String getKeyPix() {
        return keyPix;
    }

    public StatusKeyPix getStatusKeyPix() {
        return statusKeyPix;
    }

    public void setStatusKeyPix(StatusKeyPix statusKeyPix) {
        this.statusKeyPix = statusKeyPix;
    }

    public TypeKeyPix getTypeKeyPix() {
        return typeKeyPix;
    }
}

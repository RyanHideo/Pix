package org.example.pix.model;

import jakarta.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city")
    private String city;


    public Client() {
    }

    public Client(String name, String cpf, String email, String password, String phone, String address, String city) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.city = city;
    }
}

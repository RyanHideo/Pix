package model;

import jakarta.persistence.*;
import org.yaml.snakeyaml.events.Event;

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


}

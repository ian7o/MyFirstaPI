package com.rentsclients.rentsandclients.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ClientTest")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientID;
    private String firstName;
    private String lastName;
    //pois o nif é obrigatorio
    @Column(unique = true, nullable = false)
    private int nif;
    private String accountIsActivated;

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getAccountIsActivated() {
        return accountIsActivated;
    }

    public void setAccountIsActivated(String accountIsActivated) {
        this.accountIsActivated = accountIsActivated;
    }
}

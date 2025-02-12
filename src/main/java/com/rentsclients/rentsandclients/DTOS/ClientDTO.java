package com.rentsclients.rentsandclients.DTOS;

import jakarta.persistence.Column;

public class ClientDTO {
    private Long clientID;
    private String firstName;
    private String lastName;
    private int nif;
    private boolean activated;

    public ClientDTO() {
    }

    public ClientDTO(Long clientID, String firstName, String lastName, int nif, boolean activated) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nif = nif;
        this.activated = activated;
    }

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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}

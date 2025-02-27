package com.rentsclients.rentsandclients.DTOS;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    @Column(nullable = false)
    @Size(min = 3, max = 50, message = "The client first name do not approved")
    @NotBlank(message = "The client firstName is empty")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "The client lastName is empty")
    @Size(min = 3, max = 50, message = "The client last name do not approved")
    private String lastName;

    @Column(unique = true, nullable = false)
    @Min(value = 100000000, message = "The client Nif do not approved")
    @Max(value = 999999999, message = "The client Nif do not approved")
    private Integer nif;
    private boolean activated;

    public ClientDTO(String firstName, String lastName, int nif, boolean activated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nif = nif;
        this.activated = activated;
    }
}

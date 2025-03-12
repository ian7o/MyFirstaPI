package com.rentsclients.rentsandclients.DTOS;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.beans.Encoder;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    @NotNull(message = "The client firstName is empty")
    @NotBlank(message = "The client firstName is empty")
    @Size(min = 3, max = 50, message = "The client first name size do not approved")
    private String firstName;


    @NotNull(message = "The client lastName is empty")
    @NotBlank(message = "The client lastName is empty")
    @Size(min = 3, max = 50, message = "The client last name size do not approved")
    private String lastName;

    @Column(unique = true, nullable = false)
    @Min(value = 100000000, message = "The client Nif do not approved")
    @Max(value = 999999999, message = "The client Nif do not approved")
    private Integer nif;

    private String password;

    private boolean activated;
}
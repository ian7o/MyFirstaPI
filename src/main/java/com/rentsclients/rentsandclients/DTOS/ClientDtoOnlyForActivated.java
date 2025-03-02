package com.rentsclients.rentsandclients.DTOS;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDtoOnlyForActivated {
    @NotNull(message = "The client firstName is empty")
    @NotBlank(message = "The client firstName is empty")
    @Size(min = 3, max = 50, message = "The client first name size do not approved")
    private String firstName;

    @NotNull(message = "The client lastName is empty")
    @NotBlank(message = "The client lastName is empty")
    @Size(min = 3, max = 50, message = "The client last name size do not approved")
    private String lastName;

    private boolean activated;
}
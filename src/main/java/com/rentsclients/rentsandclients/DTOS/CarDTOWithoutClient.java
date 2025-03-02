package com.rentsclients.rentsandclients.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDTOWithoutClient {
    @NotNull(message = "The car brand is empty")
    @NotBlank(message = "The car brand is empty")
    @Size(min = 3, max = 50, message = "The car brand size do not approved")
    private String brand;

    @NotNull(message = "The car model is empty")
    @NotBlank(message = "The car model is empty")
    @Size(min = 3, max = 50, message = "The car model size do not approved")
    private String model;


    @NotNull(message = "The car plate is empty")
    @NotBlank(message = "The car plate is empty")
    @Size(min = 7, max = 8, message = "The car plate size do not approved")
    private String plate;

    private boolean activated;
}

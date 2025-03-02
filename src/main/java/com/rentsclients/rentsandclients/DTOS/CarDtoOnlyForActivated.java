package com.rentsclients.rentsandclients.DTOS;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDtoOnlyForActivated {
    private boolean activated;
}
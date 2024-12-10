package com.microservice.client.service.http.response;

import com.microservice.client.presentation.dto.ReservaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseClienReservaCampo {
    private String id;
    private String name;
    private String lastname;
    private int dni;
    private List<ReservaDto> reservas;
}

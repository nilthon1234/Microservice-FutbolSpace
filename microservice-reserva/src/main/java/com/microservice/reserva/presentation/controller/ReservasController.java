package com.microservice.reserva.presentation.controller;

import com.microservice.reserva.persistence.models.Reservas;
import com.microservice.reserva.presentation.dto.ReservaDto;
import com.microservice.reserva.service.http.response.ResponseReserva;
import com.microservice.reserva.service.interfaces.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservasController {

    @Autowired
    private IReservaService reservaService;

    @PostMapping("/client/register")
    public ResponseEntity<?> register(@RequestBody ReservaDto request) {
        try {
            Reservas reserva = reservaService.registrarReserva(
                    request.getCampoFutbol(),
                    request.getDniCliente(),
                    request.getFecha(),
                    request.getHoraInicio(),
                    request.getHoraFin()
            );
            System.out.println(reserva);
            return ResponseEntity.ok(reserva);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/client/search")
    public ResponseEntity<List<ResponseReserva>> listReservaDniClien(@RequestParam int dniCliente) {
         List<ResponseReserva> myList = reservaService.searchDniClient(dniCliente);
        return ResponseEntity.ok(myList);
    }

    @GetMapping("/client/{dni}")
    public ResponseEntity<List<ReservaDto>> getListDniClient(@PathVariable int dni){
        List<ReservaDto> response = reservaService.findByDniClient(dni);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/admin/list-reserva")
    public List<ReservaDto> allListReservas(){
    	return reservaService.allListReservas();
    }

}

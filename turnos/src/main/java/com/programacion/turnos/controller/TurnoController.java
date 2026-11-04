package com.programacion.turnos.controller;

import com.programacion.turnos.model.Turno;
import com.programacion.turnos.repository.TurnoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    private final TurnoRepository turnoRepository;

    public TurnoController(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @GetMapping
    public List<Turno> obtenerTodos() {
        return turnoRepository.findAll();
    }

    @GetMapping("/profesional/{profesionalId}")
    public List<Turno> obtenerPorProfesional(@PathVariable Long profesionalId) {
        return turnoRepository.findByProfesionalId(profesionalId);
    }

    @PostMapping
    public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) {
        if (turno.getEstado() == null) {
            turno.setEstado("PENDIENTE");
        }
        Turno nuevoTurno = turnoRepository.save(turno);
        return ResponseEntity.ok(nuevoTurno);
    }
}
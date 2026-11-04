package com.programacion.turnos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profesional_id", nullable = false)
    private Long profesionalId;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private String estado; // PENDIENTE, CONFIRMADO, CANCELADO

    private String clienteNombre;
    private String clienteTelefono;
    private String observaciones;

    public Turno() {
    }

    public Turno(Long profesionalId, LocalDateTime fechaHora, String estado, String clienteNombre, String clienteTelefono, String observaciones) {
        this.profesionalId = profesionalId;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.clienteNombre = clienteNombre;
        this.clienteTelefono = clienteTelefono;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProfesionalId() { return profesionalId; }
    public void setProfesionalId(Long profesionalId) { this.profesionalId = profesionalId; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }

    public String getClienteTelefono() { return clienteTelefono; }
    public void setClienteTelefono(String clienteTelefono) { this.clienteTelefono = clienteTelefono; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
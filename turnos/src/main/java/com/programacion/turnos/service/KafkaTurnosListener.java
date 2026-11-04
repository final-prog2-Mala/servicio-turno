package com.programacion.turnos.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programacion.turnos.model.Turno;
import com.programacion.turnos.repository.TurnoRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaTurnosListener {

    private final TurnoRepository turnoRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaTurnosListener(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @KafkaListener(topics = "${catedra.kafka.topic.appointment-actions}", groupId = "${spring.kafka.consumer.group-id}")
    public void escucharAccionesTurnos(String mensaje) {
        System.out.println("=========================================");
        System.out.println("¡EVENTO DE TURNO RECIBIDO (Acciones)!");
        System.out.println("Payload: " + mensaje);

        try {
            // Si el mensaje viene en formato JSON representativo de un Turno
            Turno turno = objectMapper.readValue(mensaje, Turno.class);
            turnoRepository.save(turno);
            System.out.println("¡Turno guardado/actualizado correctamente en PostgreSQL!");
        } catch (Exception e) {
            System.out.println("Mensaje recibido en formato texto plano o estructura previa: " + e.getMessage());
        }
        System.out.println("=========================================");
    }

    @KafkaListener(topics = "${catedra.kafka.topic.appointment-phone}", groupId = "${spring.kafka.consumer.group-id}")
    public void escucharTelefonoTurnos(String mensaje) {
        System.out.println("=========================================");
        System.out.println("¡EVENTO RECIBIDO EN TÓPICO TELÉFONO!");
        System.out.println("Payload: " + mensaje);
        System.out.println("=========================================");
    }
}
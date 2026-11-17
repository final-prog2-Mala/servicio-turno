package com.programacion.turnos.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.programacion.turnos.model.Turno;
import com.programacion.turnos.repository.TurnoRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaTurnosListener {

    private final TurnoRepository turnoRepository;
    private final ObjectMapper objectMapper;

    public KafkaTurnosListener(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
        this.objectMapper = new ObjectMapper()
                .findAndRegisterModules()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @KafkaListener(topics = "${catedra.kafka.topic.appointment-actions}", groupId = "${spring.kafka.consumer.group-id}")
    public void escucharAccionesTurnos(String mensaje) {
        System.out.println("=========================================");
        System.out.println("¡EVENTO DE TURNO RECIBIDO (Acciones)!");
        System.out.println("Payload: " + mensaje);

        try {
            Turno turno = objectMapper.readValue(mensaje, Turno.class);
            turnoRepository.save(turno);
            System.out.println("¡Turno guardado/actualizado correctamente en PostgreSQL!");
        } catch (Exception e) {
            System.err.println("No se pudo mapear directamente a Turno: " + e.getMessage());
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
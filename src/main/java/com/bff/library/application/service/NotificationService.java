package com.bff.library.application.service;

import com.bff.library.application.port.in.NotificationUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class NotificationService implements NotificationUseCase {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final JdbcTemplate jdbcTemplate;
    private final WebClient webClient;

    @Value("${azure.functions.productor.url}")
    private String productorUrl;

    public NotificationService(JdbcTemplate jdbcTemplate, WebClient.Builder webClientBuilder) {
        this.jdbcTemplate = jdbcTemplate;
        this.webClient = webClientBuilder.build();
    }

    @Override
    public void executeJobNotification() {
        logger.info("Iniciando JOB de notificaciones...");

        String sql = "SELECT DISTINCT u.id, u.email FROM USUARIOS u " +
                     "JOIN RESERVAS r ON u.id = r.user_id " +
                     "WHERE r.status = 'ACTIVA' OR r.status = 'PENDIENTE'";

        logger.info("Ejecutando query en base de datos para buscar usuarios con reservas pendientes...");
        List<Map<String, Object>> usuariosPendientes = jdbcTemplate.queryForList(sql);

        if (usuariosPendientes.isEmpty()) {
            logger.info("No se encontraron usuarios con reservas pendientes.");
            return;
        }

        logger.info("Se encontraron {} usuarios. Enviando lista a la función productora...", usuariosPendientes.size());

        try {
            webClient.post()
                    .uri(productorUrl)
                    .bodyValue(usuariosPendientes)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            logger.info("Lista de usuarios enviada exitosamente al productor.");
        } catch (Exception e) {
            logger.error("Error al enviar lista de usuarios al productor: {}", e.getMessage(), e);
        }
    }
}

package com.bff.library.application.service;

import com.bff.library.application.port.in.NotificationUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class NotificationService implements NotificationUseCase {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final JdbcTemplate jdbcTemplate;
    private final WebClient webClient;

    @Value("${azure.functions.productor.url}")
    private String productorUrl;

    // 1. DTO interno para asegurar que los datos sean consistentes
    public record UserNotificationDto(Long id, String email) {
    }

    public NotificationService(JdbcTemplate jdbcTemplate, WebClient.Builder webClientBuilder) {
        this.jdbcTemplate = jdbcTemplate;
        this.webClient = webClientBuilder.build();
    }

    @Override
    @Transactional(readOnly = true) // 2. Optimización de conexión
    public void executeJobNotification() {
        logger.info("Iniciando JOB de notificaciones...");

        // 3. Query optimizada con EXISTS (más rápida que JOIN + DISTINCT)
        String sql = """
                    SELECT u.id, u.email FROM USUARIOS u
                    WHERE EXISTS (
                        SELECT 1 FROM RESERVAS r
                        WHERE r.user_id = u.id
                        AND r.status IN ('ACTIVA', 'PENDIENTE')
                    )
                """;

        try {
            logger.info("Buscando usuarios con reservas pendientes...");

            // 4. Mapeo explícito a DTO para evitar sorpresas con tipos de datos
            List<UserNotificationDto> usuarios = jdbcTemplate.query(sql, (rs, rowNum) -> new UserNotificationDto(
                    rs.getLong("id"),
                    rs.getString("email")));

            if (usuarios.isEmpty()) {
                logger.info("No se encontraron usuarios pendientes.");
                return;
            }

            enviarAProductor(usuarios);

        } catch (Exception e) {
            logger.error("Error crítico en el acceso a datos: {}", e.getMessage());
        }
    }

    private void enviarAProductor(List<UserNotificationDto> usuarios) {
        logger.info("Enviando {} usuarios al productor...", usuarios.size());
        webClient.post()
                .uri(productorUrl)
                .bodyValue(usuarios)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(v -> logger.info("Envío exitoso."))
                .doOnError(e -> logger.error("Error en la llamada REST: {}", e.getMessage()))
                // Usamos subscribe() o block() dependiendo de si el Job es síncrono
                .block();
    }
}
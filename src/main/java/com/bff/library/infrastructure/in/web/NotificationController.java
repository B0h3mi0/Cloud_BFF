package com.bff.library.infrastructure.in.web;

import com.bff.library.application.port.in.NotificationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NotificationController {

    private final NotificationUseCase notificationUseCase;

    public NotificationController(NotificationUseCase notificationUseCase) {
        this.notificationUseCase = notificationUseCase;
    }

    @GetMapping("/notificar-pendientes")
    public ResponseEntity<String> notificarPendientes() {
        notificationUseCase.executeJobNotification();
        return ResponseEntity.ok("Proceso de notificación iniciado.");
    }
}

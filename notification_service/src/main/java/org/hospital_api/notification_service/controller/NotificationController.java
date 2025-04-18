package org.hospital_api.notification_service.controller;

import org.hospital_api.notification_service.service_impl.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notification/send")
    public ResponseEntity<String> send(){
        return new ResponseEntity<>("The message was sent", HttpStatus.OK);
    }
}

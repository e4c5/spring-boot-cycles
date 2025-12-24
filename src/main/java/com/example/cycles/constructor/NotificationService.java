package com.example.cycles.constructor;

import org.springframework.stereotype.Service;

/**
 * Constructor injection cycle: NotificationService depends on UserService via
 * constructor.
 * This creates a hard cycle that cannot be resolved with simple @Lazy.
 */
@Service
public class NotificationService {

    private final UserService userService;

    public NotificationService(UserService userService) {
        this.userService = userService;
    }

    public void sendWelcomeEmail(String email) {
        System.out.println("Sending welcome email to: " + email);
        userService.updateEmailSentStatus(email, true);
    }

    public void sendNotification(String email, String message) {
        String user = userService.getUserByEmail(email);
        System.out.println("Sending to " + user + ": " + message);
    }
}

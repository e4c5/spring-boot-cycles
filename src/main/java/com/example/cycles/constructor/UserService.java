package com.example.cycles.constructor;

import org.springframework.stereotype.Service;

/**
 * Constructor injection cycle: UserService depends on NotificationService via
 * constructor.
 * This is a "hard" cycle that Spring CANNOT resolve - it will fail with
 * BeanCurrentlyInCreationException even in Spring Boot 2.5.
 */
@Service
public class UserService {

    private final NotificationService notificationService;

    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void createUser(String email) {
        System.out.println("Creating user: " + email);
        notificationService.sendWelcomeEmail(email);
    }

    public void updateEmailSentStatus(String email, boolean sent) {
        System.out.println("Email sent status for " + email + ": " + sent);
    }

    public String getUserByEmail(String email) {
        return "User-" + email;
    }
}

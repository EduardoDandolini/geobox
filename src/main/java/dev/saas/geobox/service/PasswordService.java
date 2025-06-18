package dev.saas.geobox.service;

public interface PasswordService {

    void sendResetLink(String email);

    void resetPassword(String token, String newPassword);
}

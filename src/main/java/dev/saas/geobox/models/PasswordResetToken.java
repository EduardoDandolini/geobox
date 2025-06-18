package dev.saas.geobox.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PasswordResetToken extends BaseEntity{

    private String token;

    @OneToOne
    private User user;

    private LocalDateTime expirationDate;

    private boolean used = false;
}

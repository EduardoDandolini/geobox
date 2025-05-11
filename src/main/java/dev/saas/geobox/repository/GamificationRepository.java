package dev.saas.geobox.repository;

import dev.saas.geobox.models.Gamification;
import dev.saas.geobox.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GamificationRepository extends JpaRepository<Gamification, Long> {

    Optional<Gamification> findByUser(User user);

    @Query("select g from Gamification g order by g.points desc")
    List<Gamification> findAllPoints();

}

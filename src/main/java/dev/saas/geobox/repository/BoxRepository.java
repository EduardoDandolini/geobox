package dev.saas.geobox.repository;

import dev.saas.geobox.models.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {

    Optional<Box> findByBoxNumber(String boxNumber);
}

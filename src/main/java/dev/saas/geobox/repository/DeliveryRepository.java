package dev.saas.geobox.repository;

import dev.saas.geobox.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Query("select d from Delivery d where d.status <> 2")
    List<Delivery> findDeliveryStatus();
}

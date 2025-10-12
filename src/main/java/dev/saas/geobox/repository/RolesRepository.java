package dev.saas.geobox.repository;

import dev.saas.geobox.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, String> {

    @Query("select r from tb_roles r where r.identifier in :ids")
    Optional<Roles> findRolesById(@Param("ids") List<String> ids);

}

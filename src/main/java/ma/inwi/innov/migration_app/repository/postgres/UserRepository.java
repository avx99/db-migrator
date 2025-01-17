package ma.inwi.innov.migration_app.repository.postgres;

import ma.inwi.innov.migration_app.domain.postgres.Event;
import ma.inwi.innov.migration_app.domain.postgres.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUsername(String username);
  Optional<User> findFirstByEmail(String email);
  Page<User> findByEventsContaining(Event event, Pageable pageable);
  List<User> findByEventsContaining(Event event);

  @Query(value = "select keycloak_id from innov.user u where u.version = :version", nativeQuery = true)
  List<String> findAllKeycloakIdsByVersion(@Param("version") String version);

  @Modifying
  @Query(value = "delete from innov.user u where u.version = '1.0.0'", nativeQuery = true)
  void deleteUserByVersion();
}
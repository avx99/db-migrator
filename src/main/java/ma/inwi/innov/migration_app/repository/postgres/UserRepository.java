package ma.inwi.innov.migration_app.repository.postgres;

import ma.inwi.innov.migration_app.domain.postgres.Event;
import ma.inwi.innov.migration_app.domain.postgres.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUsername(String username);
  Page<User> findByEventsContaining(Event event, Pageable pageable);
  List<User> findByEventsContaining(Event event);
}
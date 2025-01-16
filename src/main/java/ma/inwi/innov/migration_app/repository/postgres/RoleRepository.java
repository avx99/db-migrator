package ma.inwi.innov.migration_app.repository.postgres;

import ma.inwi.innov.migration_app.domain.postgres.Role;
import ma.inwi.innov.migration_app.domain.postgres.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByCode(String code);

}
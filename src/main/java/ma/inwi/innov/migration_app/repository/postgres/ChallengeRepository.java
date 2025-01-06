package ma.inwi.innov.migration_app.repository.postgres;

import ma.inwi.innov.migration_app.domain.postgres.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {

    List<Challenge> findAllByOrderByCreatedAtDescUpdatedAtDesc();
}

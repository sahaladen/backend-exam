package examjava.candyco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandCoRepo extends JpaRepository<CandyCo, Long> {
}

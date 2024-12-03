package org.example.javaexam.candyco;

import org.springframework.stereotype.Repository;

@Repository
public interface CandCoRepo extends JpaRepository<Patron, Long>{
}

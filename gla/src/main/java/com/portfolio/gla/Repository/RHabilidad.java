
package com.portfolio.gla.Repository;

import com.portfolio.gla.Entity.Habilidad;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RHabilidad extends JpaRepository<Habilidad, Integer> {
    public Optional<Habilidad> findByNombreH(String NombreH);
    public boolean existsByNombreH(String NombreH);
}

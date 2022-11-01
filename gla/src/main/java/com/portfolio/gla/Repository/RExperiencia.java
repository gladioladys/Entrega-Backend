
package com.portfolio.gla.Repository;

import com.portfolio.gla.Entity.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RExperiencia extends JpaRepository<Experiencia, Integer> {
    public Optional<Experiencia> findByNombreE(String NombreE);
    public boolean existsByNombreE(String NombreE);
}

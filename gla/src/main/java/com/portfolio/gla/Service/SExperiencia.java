package com.portfolio.gla.Service;

import com.portfolio.gla.Entity.Experiencia;
import com.portfolio.gla.Repository.RExperiencia;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SExperiencia {

    @Autowired
    RExperiencia rExperiencia;

    public List<Experiencia> traerExperiencias() {
        return rExperiencia.findAll();
    }

    public Optional<Experiencia> traerUno(int id) {
        return rExperiencia.findById(id);
    }

    public Optional<Experiencia> traerByNombreE(String nombreE) {
        return rExperiencia.findByNombreE(nombreE);
    }
    
    public void saveExperiencia(Experiencia expe){
        rExperiencia.save(expe);
    }
    
    public void deleteExperiencia(int id){
        rExperiencia.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rExperiencia.existsById(id);
    }
    
    public boolean existsByNombre(String nombreE){
        return rExperiencia.existsByNombreE(nombreE);
    }
}

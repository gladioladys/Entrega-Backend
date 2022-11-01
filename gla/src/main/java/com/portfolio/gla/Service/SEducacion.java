package com.portfolio.gla.Service;

import com.portfolio.gla.Entity.Educacion;
import com.portfolio.gla.Repository.REducacion;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SEducacion {

    @Autowired
    REducacion rEducacion;

    public List<Educacion> ListarEducacion() {
        return rEducacion.findAll();
    }

    public Optional<Educacion> traerUno(int id) {
        return rEducacion.findById(id);
    }

    public Optional<Educacion> traerByNombreE(String nombreE) {
        return rEducacion.findByNombreE(nombreE);
    }

    public void saveEducacion(Educacion educacion) {
        rEducacion.save(educacion);
    }

    public void deleteEducacion(int id) {
        rEducacion.deleteById(id);
    }

    public boolean existsById(int id) {
        return rEducacion.existsById(id);
    }

    public boolean existsByNombre(String nombreE) {
        return rEducacion.existsByNombreE(nombreE);
    }
}

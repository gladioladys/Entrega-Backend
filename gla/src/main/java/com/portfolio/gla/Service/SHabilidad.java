
package com.portfolio.gla.Service;

import com.portfolio.gla.Entity.Habilidad;
import com.portfolio.gla.Repository.RHabilidad;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SHabilidad {
    @Autowired
    RHabilidad rHabilidad;
    
    public List<Habilidad> listarHabilidad(){
        return rHabilidad.findAll();
    }
    
    public Optional<Habilidad> traerUno(int id){
        return rHabilidad.findById(id);        
    }
    
    public Optional<Habilidad> traerByNombreH(String nombreH){
        return rHabilidad.findByNombreH(nombreH);        
    }
    
    public void saveHabilidad(Habilidad habilidad){
        rHabilidad.save(habilidad);
    }
    
    public void deleteHabilidad(int id){
        rHabilidad.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rHabilidad.existsById(id);
    }
    
    public boolean existsByNombreH(String nombreH){
        return rHabilidad.existsByNombreH(nombreH);
    }
}

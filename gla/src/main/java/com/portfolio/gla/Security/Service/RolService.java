package com.portfolio.gla.Security.Service;

import com.portfolio.gla.Security.Entity.Rol;
import com.portfolio.gla.Security.Enums.RolNombre;
import com.portfolio.gla.Security.Repository.IRolRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
    @Autowired 
    public IRolRepository iRolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
         return iRolRepository.findByRolNombre(rolNombre);         
    }
    
    public void save(Rol rol){
        iRolRepository.save(rol);
    } 
    
}
  
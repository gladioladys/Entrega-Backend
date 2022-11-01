package com.portfolio.gla.Interface;

import com.portfolio.gla.Entity.Persona;
import java.util.List;

public interface IPersonaService {
//  Traer Lista de Personas
    public List<Persona> getPersonas();
   
//  Guardar un objeto de tipo Persona    
    public void savePersona(Persona persona);
    
//  Eliminar objeto por ID   
    public void deletePersona(Long id);
    
//  Buscar una Persona por ID   
    public Persona findPersona(Long id);
    
}

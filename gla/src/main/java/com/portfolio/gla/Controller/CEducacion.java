package com.portfolio.gla.Controller;

import com.portfolio.gla.Dto.dtoEducacion;
import com.portfolio.gla.Entity.Educacion;
import com.portfolio.gla.Security.Controller.Mensaje;
import com.portfolio.gla.Service.SEducacion;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://frontendgla.web.app")
public class CEducacion {
    
    @Autowired
    SEducacion sEducacion;
    
    @GetMapping("/listar")
    public ResponseEntity<List<Educacion>> listar(){
        List<Educacion> lista = sEducacion.ListarEducacion();
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        // Valido si Existe el ID        
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        
        Educacion educacion = sEducacion.traerUno(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> deleteEducacion(@PathVariable("id") int id){
        // Valido si Existe el ID        
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        
        sEducacion.deleteEducacion(id);
        return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody dtoEducacion dtoEdu) {
        if (StringUtils.isBlank(dtoEdu.getNombreE())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sEducacion.existsByNombre(dtoEdu.getNombreE())) {
            return new ResponseEntity(new Mensaje("Esa educacion ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = new Educacion(dtoEdu.getNombreE(), dtoEdu.getDescripcionE());
        sEducacion.saveEducacion(educacion);
        
        return new ResponseEntity(new Mensaje("Educacion agregada"), HttpStatus.OK); 
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") int id, @RequestBody dtoEducacion dtoEdu){
        // Valido si Existe el ID        
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        // Valido si Existe el nombre de Educacion
        if (sEducacion.existsByNombre(dtoEdu.getNombreE()) && sEducacion.traerByNombreE(dtoEdu.getNombreE()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Esa educacion ya existe"), HttpStatus.BAD_REQUEST);
        }
        //  El nombre no puede estar vacio
        if (StringUtils.isBlank(dtoEdu.getNombreE())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = sEducacion.traerUno(id).get();
        educacion.setNombreE(dtoEdu.getNombreE());
        educacion.setDescripcionE(dtoEdu.getDescripcionE());
        
        sEducacion.saveEducacion(educacion);
        
        return new ResponseEntity(new Mensaje("Educacion actualizada"), HttpStatus.OK);
    }

}

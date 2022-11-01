package com.portfolio.gla.Controller;

import com.portfolio.gla.Dto.dtoHabilidad;
import com.portfolio.gla.Entity.Habilidad;
import com.portfolio.gla.Security.Controller.Mensaje;
import com.portfolio.gla.Service.SHabilidad;
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
@RequestMapping("/habilidad")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://frontendgla.web.app")
public class CHabilidad {

    @Autowired
    SHabilidad sHabilidad;

    @GetMapping("/listar")
    public ResponseEntity<List<Habilidad>> listar() {
        List<Habilidad> lista = sHabilidad.listarHabilidad();
        return new ResponseEntity(lista, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Habilidad> getById(@PathVariable("id") int id) {
        // Valido si Existe el ID        
        if (!sHabilidad.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }

        Habilidad habilidad = sHabilidad.traerUno(id).get();
        return new ResponseEntity(habilidad, HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> deleteHabilidad(@PathVariable("id") int id) {
        // Valido si Existe el ID        
        if (!sHabilidad.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }

        sHabilidad.deleteHabilidad(id);
        return new ResponseEntity(new Mensaje("Habilidad eliminada"), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody dtoHabilidad dtoHabi) {
        if (StringUtils.isBlank(dtoHabi.getNombreH())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sHabilidad.existsByNombreH(dtoHabi.getNombreH())) {
            return new ResponseEntity(new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        }

        Habilidad habilidad = new Habilidad(dtoHabi.getNombreH(), dtoHabi.getPorcentajeH());
        sHabilidad.saveHabilidad(habilidad);

        return new ResponseEntity(new Mensaje("Habilidad agregada"), HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") int id, @RequestBody dtoHabilidad dtoHabi) {
        // Valido si Existe el ID        
        if (!sHabilidad.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        // Valido si Existe el nombre de Habilidad
        if (sHabilidad.existsByNombreH(dtoHabi.getNombreH()) && sHabilidad.traerByNombreH(dtoHabi.getNombreH()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        }
        //  El nombre no puede estar vacio
        if (StringUtils.isBlank(dtoHabi.getNombreH())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Habilidad habilidad = sHabilidad.traerUno(id).get();
        habilidad.setNombreH(dtoHabi.getNombreH());
        habilidad.setPorcentajeH(dtoHabi.getPorcentajeH());

        sHabilidad.saveHabilidad(habilidad);

        return new ResponseEntity(new Mensaje("Habilidad actualizada"), HttpStatus.OK);
    }
}

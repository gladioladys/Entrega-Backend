
package com.portfolio.gla.Dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class dtoHabilidad {
    @NotBlank
    private String nombreH;
    @NotBlank
    private int porcentajeH;

    //Constructores
    
    public dtoHabilidad() {
    }

    public dtoHabilidad(String nombreH, int porcentajeH) {
        this.nombreH = nombreH;
        this.porcentajeH = porcentajeH;
    }
      
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo7.finaldw.model;

import java.util.Date;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author carlos
 */
public class VuelosModel {
    
    private int id;
    @NotEmpty
    private String salida;
    @NotEmpty
    private String destino;
    
    //private Date fechaHoraSalida;
    
    //private Date fechaHoraLlegada;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    public VuelosModel() {
        super();
    }

    public VuelosModel(int id, String salida, String destino) {
        super();
        this.id = id;
        this.salida = salida;
        this.destino = destino;
        //this.fechaHoraSalida = fechaHoraSalida;
        //this.fechaHoraLlegada = fechaHoraLlegada;
        
    }
    
}

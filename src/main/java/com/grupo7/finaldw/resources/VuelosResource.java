/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo7.finaldw.resources;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.http.HttpStatus;

import com.codahale.metrics.annotation.Timed;
import com.grupo7.finaldw.api.Representation;
import com.grupo7.finaldw.model.VuelosModel;
import com.grupo7.finaldw.services.VuelosService;

/**
 *
 * @author carlos
 */

@Path("/vuelos")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("ADMIN")
public class VuelosResource {
    private final VuelosService vuelosService;

    public VuelosResource(VuelosService vuelosService) {
        this.vuelosService = vuelosService;
    }

    @GET
    @Timed
    public Representation<List<VuelosModel>> getVuelos() {
        return new Representation<List<VuelosModel>>(HttpStatus.OK_200, (List<VuelosModel>) vuelosService.getVuelos());
    }

    @GET
    @Timed
    @Path("{id}")
    public Representation<VuelosModel> getVuelo(@PathParam("id") final int id) {
        return new Representation<VuelosModel>(HttpStatus.OK_200, (VuelosModel) vuelosService.getVuelo(id));
    }

    /*
    @POST
    @Timed
    public Representation<Part> createPart(@NotNull @Valid final Part part) {
        return new Representation<Part>(HttpStatus.OK_200, (Part) partsService.createPart((Part) (javax.servlet.http.Part) part));
    } */
    @POST
    @Timed
    public Representation<VuelosModel> createVuelo(@NotNull @Valid final VuelosModel vuelo) {
        return new Representation<VuelosModel>(HttpStatus.OK_200, vuelosService.createVuelo(vuelo));
    }

    @PUT
    @Timed
    @Path("{id}")
    public Representation<VuelosModel> editVuelo(@NotNull @Valid final VuelosModel vuelo,
            @PathParam("id") final int id) {
        vuelo.setId(id);
        return new Representation<VuelosModel>(HttpStatus.OK_200, vuelosService.editVuelo(vuelo));
    }

    @DELETE
    @Timed
    @Path("{id}")
    public Representation<String> deleteVuelo(@PathParam("id") final int id) {
        return new Representation<String>(HttpStatus.OK_200, vuelosService.deleteVuelo(id));
    }
}

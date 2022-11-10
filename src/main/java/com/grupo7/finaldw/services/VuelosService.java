/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo7.finaldw.services;

import com.grupo7.finaldw.dao.VuelosDao;
import com.grupo7.finaldw.model.VuelosModel;
import java.util.List;
import java.util.Objects;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.skife.jdbi.v2.exceptions.UnableToObtainConnectionException;
import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

/**
 *
 * @author carlos
 */
public abstract class VuelosService {
    private static final String PART_NOT_FOUND = "Vuelo id %s not found.";
    private static final String DATABASE_REACH_ERROR
            = "Could not reach the MySQL database. The database may be down or there may be network connectivity issues. Details: ";
    private static final String DATABASE_CONNECTION_ERROR
            = "Could not create a connection to the MySQL database. The database configurations are likely incorrect. Details: ";
    private static final String DATABASE_UNEXPECTED_ERROR
            = "Unexpected error occurred while attempting to reach the database. Details: ";
    private static final String SUCCESS = "Success...";
    private static final String UNEXPECTED_ERROR = "An unexpected error occurred while deleting part.";

    @CreateSqlObject
    abstract VuelosDao vuelosDao();

    public List<VuelosModel> getVuelos() {
        return vuelosDao().getVuelos();
    }

    public VuelosModel getVuelo(int id) {
        VuelosModel vuelo = vuelosDao().getVuelo(id);
        if (Objects.isNull(vuelo)) {
            throw new WebApplicationException(String.format(PART_NOT_FOUND, id), Status.NOT_FOUND);
        }
        return vuelo;
    }

    public VuelosModel createVuelo(VuelosModel vuelo) {
        vuelosDao().createVuelo(vuelo);
        return vuelosDao().getVuelo(vuelosDao().lastInsertId());
    }

    public VuelosModel editVuelo(VuelosModel vuelo) {
        if (Objects.isNull(vuelosDao().getVuelo(vuelo.getId()))) {
            throw new WebApplicationException(String.format(PART_NOT_FOUND, vuelo.getId()),
                    Status.NOT_FOUND);
        }
        vuelosDao().editVuelo(vuelo);
        return vuelosDao().getVuelo(vuelo.getId());
    }

    public String deleteVuelo(final int id) {
        int result = vuelosDao().deleteVuelo(id);
        switch (result) {
            case 1:
                return SUCCESS;
            case 0:
                throw new WebApplicationException(String.format(PART_NOT_FOUND, id), Status.NOT_FOUND);
            default:
                throw new WebApplicationException(UNEXPECTED_ERROR, Status.INTERNAL_SERVER_ERROR);
        }
    }

    public String performHealthCheck() {
        try {
            vuelosDao().getVuelos();
        } catch (UnableToObtainConnectionException ex) {
            return checkUnableToObtainConnectionException(ex);
        } catch (UnableToExecuteStatementException ex) {
            return checkUnableToExecuteStatementException(ex);
        } catch (Exception ex) {
            return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
        }
        return null;
    }

    private String checkUnableToObtainConnectionException(UnableToObtainConnectionException ex) {
        if (ex.getCause() instanceof java.sql.SQLNonTransientConnectionException) {
            return DATABASE_REACH_ERROR + ex.getCause().getLocalizedMessage();
        } else if (ex.getCause() instanceof java.sql.SQLException) {
            return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
        } else {
            return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
        }
    }

    private String checkUnableToExecuteStatementException(UnableToExecuteStatementException ex) {
        if (ex.getCause() instanceof java.sql.SQLSyntaxErrorException) {
            return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
        } else {
            return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
        }
    }
}

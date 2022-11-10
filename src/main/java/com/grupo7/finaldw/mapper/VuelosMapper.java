/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo7.finaldw.mapper;

import com.grupo7.finaldw.model.VuelosModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 *
 * @author carlos
 */
public class VuelosMapper implements ResultSetMapper<VuelosModel> {
    private static final String ID = "id";
 private static final String SALIDA = "salida";
 private static final String DESTINO = "destino";
 //private static final String FECHA_HORA_SALIDA = "fecha_hora_salida";
 //private static final String FECHA_HORA_LLEGADA = "fecha_hora_llegada";

 public VuelosModel map(int i, ResultSet resultSet, StatementContext statementContext)
     throws SQLException {
   return new VuelosModel(resultSet.getInt(ID), resultSet.getString(SALIDA), resultSet.getString(DESTINO));
 }
}

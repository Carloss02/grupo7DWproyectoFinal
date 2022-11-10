/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo7.finaldw.mapper;

import com.grupo7.finaldw.model.Part;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 *
 * @author alumno
 */
public class PartsMapper implements ResultSetMapper<Part> {
 private static final String ID = "id";
 private static final String NAME = "name";
 private static final String CODE = "code";

 public Part map(int i, ResultSet resultSet, StatementContext statementContext)
     throws SQLException {
   return new Part(resultSet.getInt(ID), resultSet.getString(NAME), resultSet.getString(CODE));
 }
}

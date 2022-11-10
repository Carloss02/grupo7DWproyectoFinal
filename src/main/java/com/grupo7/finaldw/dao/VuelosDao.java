/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo7.finaldw.dao;

import com.grupo7.finaldw.mapper.VuelosMapper;
import com.grupo7.finaldw.model.VuelosModel;

import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 *
 * @author carlo
 */
@RegisterMapper(VuelosMapper.class)
public interface VuelosDao {
    @SqlQuery("select * from vuelos;")
    public List<VuelosModel> getVuelos();

    @SqlQuery("select * from vuelos where id = :id")
    public VuelosModel getVuelo(@Bind("id") final int id);

    @SqlUpdate("insert into vuelos(salida, destino) values(:salida, :destino)")
    void createVuelo(@BindBean final VuelosModel vuelo);

    @SqlUpdate("update vuelos set salida = coalesce(:salida, salida), destino = coalesce(:destino, destino) where id = :id")
    void editVuelo(@BindBean final VuelosModel vuelo);

    @SqlUpdate("delete from vuelos where id = :id")
    int deleteVuelo(@Bind("id") final int id);

    @SqlQuery("select last_insert_id();")
    public int lastInsertId();
}

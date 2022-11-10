/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grupo7.finaldw.dao;

import com.grupo7.finaldw.mapper.PartsMapper;
import com.grupo7.finaldw.model.Part;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;


import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 *
 * @author alumno
 */
@RegisterMapper(PartsMapper.class)
public interface PartsDao {

    @SqlQuery("select * from parts;")
    public List<Part> getParts();

    @SqlQuery("select * from parts where id = :id")
    public Part getPart(@Bind("id") final int id);

    @SqlUpdate("insert into parts(name, code) values(:name, :code)")
    void createPart(@BindBean final Part part);

    @SqlUpdate("update parts set name = coalesce(:name, name), code = coalesce(:code, code) where id = :id")
    void editPart(@BindBean final Part part);

    @SqlUpdate("delete from parts where id = :id")
    int deletePart(@Bind("id") final int id);

    @SqlQuery("select last_insert_id();")
    public int lastInsertId();
}

package com.michael.courser.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person>  {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person p = new Person(String.valueOf(rs.getInt("id")));
        p.setFirstName(rs.getString("first_name"));
        p.setLastName(rs.getString("last_name"));
        return p;
    }
}

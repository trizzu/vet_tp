package com.example.demo.dao;

import com.example.demo.model.Turno;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TurnoDao {
    private Connection connection;

    public TurnoDao() throws SQLException{
        connection = ConexionMySql.getInstancia().getConnection();
    }

    public List<Turno> obtenerTurnos(){
        List<Turno> turnos = new ArrayList<>();
        String sql = "select * from Turno";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Turno turno = new Turno(rs.getInt("id_turno"), rs.getString("fehca"), rs.getString("hora"), rs.getString("motivo"), rs.getString("estado"), rs.getInt("id_cliente"), rs.getInt("id_veterinario"), rs.getInt("id_mascota"));
                turnos.add(turno);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return turnos;
    }
}

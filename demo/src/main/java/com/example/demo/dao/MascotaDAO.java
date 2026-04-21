package com.example.demo.dao;

import com.example.demo.model.Mascota;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {
    private Connection connection;

    public MascotaDAO() throws SQLException{
        connection = ConexionMySql.getInstancia().getConnection();
    }

    public List<Mascota> obtenerMascotas(){
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "select * from Mascota";
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Mascota mascota = new Mascota(rs.getInt("id_mascota"), rs.getString("nombre"), rs.getString("especie"), rs.getString("raza"), rs.getInt("edad"), rs.getDouble("peso"), rs.getInt("id_cliente"));
                mascotas.add(mascota);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return mascotas;
    }
}

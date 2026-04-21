package com.example.demo.dao;

import com.example.demo.model.Mascota;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {
    private Connection connection;

    public MascotaDAO() throws SQLException{
        connection = ConexionMySql.getInstancia().getConnection();
    }

    public List<Mascota> obtenerMascotas(){
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "select * from Mascota as m where (select activo from clientes as c where m.id_cliente = c.id_cliente) = true";
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

    public List<Mascota> obtenerMascotasCliente(int id_cliente){
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "select * from Mascota where id_cliente = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id_cliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()){
                    Mascota mascota = new Mascota(rs.getInt("id_mascota"), rs.getString("nombre"), rs.getString("especie"), rs.getString("raza"), rs.getInt("edad"), rs.getDouble("peso"), id_cliente);
                    mascotas.add(mascota);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return mascotas;
    }
}

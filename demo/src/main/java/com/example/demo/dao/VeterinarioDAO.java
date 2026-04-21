package com.example.demo.dao;

import com.example.demo.model.Veterinario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {
    private Connection connection;

    public VeterinarioDAO() throws SQLException{
        connection = ConexionMySql.getInstancia().getConnection();
    }

    public List<Veterinario> obtenerVeterinarios(){
        List<Veterinario> veterinarios = new ArrayList<>();
        String sql = "select * from Veterinario";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Veterinario veterinario = new Veterinario(rs.getInt("id_veterinario"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("matricula"), rs.getString("especialidad"), rs.getString("telefono"), rs.getString("email"));
                veterinarios.add(veterinario);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return veterinarios;
    }
}

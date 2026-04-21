package com.example.demo.dao;

import com.example.demo.model.Turno;

import java.sql.*;
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

    public Turno buscarTurnoPorId(int id_turno){
        Turno turno = new Turno();
        turno.setId_turno(id_turno);
        String sql = "select * from Turno where id_turno = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id_turno);
            try (ResultSet rs = stmt.executeQuery()){
                turno.setFecha(rs.getString("fecha"));
                turno.setHora(rs.getString("hora"));
                turno.setMotivo(rs.getString("motivo"));
                turno.setEstado(rs.getString("estado"));
                turno.setId_cliente(rs.getInt("id_cliente"));
                turno.setId_veterinario(rs.getInt("id_veterinario"));
                turno.setId_mascota(rs.getInt("id_mascota"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return turno;
    }

    public void insertarTurno (Turno turno){
        String sql = "insert into Turno (fecha, hora, motivo, estado, id_cliente, id_veterinario, id_mascota) values (?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, turno.getFecha());
            pstmt.setString(2, turno.getHora());
            pstmt.setString(3, turno.getMotivo());
            pstmt.setString(4, turno.getEstado());
            pstmt.setInt(5, turno.getId_cliente());
            pstmt.setInt(6, turno.getId_veterinario());
            pstmt.setInt(7, turno.getId_mascota());
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateTurno (Turno turno){
        String sql = "update Turno set fecha = ?, hora = ?, motivo = ?, estado = ?, id_cliente = ?, id_veterinario = ?, id_mascota = ? where id_turno = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, turno.getFecha());
            pstmt.setString(2, turno.getHora());
            pstmt.setString(3, turno.getMotivo());
            pstmt.setString(4, turno.getEstado());
            pstmt.setInt(5, turno.getId_cliente());
            pstmt.setInt(6, turno.getId_veterinario());
            pstmt.setInt(7, turno.getId_mascota());
            pstmt.setInt(8, turno.getId_turno());
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteTurno(int id_turno){
        String sql = "delete from Turno where id_turno = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
                pstmt.setInt(1, id_turno);
                pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Turno> obtenerTurnosVeterinario(int id_veterinario){
        List<Turno> turnos = new ArrayList<>();
        String sql = "select * from Turno where id_veterinario = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, id_veterinario);
            try (ResultSet rs = pstmt.executeQuery()){
                Turno turno = new Turno(rs.getInt("id_turno"), rs.getString("fecha"), rs.getString("hora"), rs.getString("motivo"), rs.getString("estado"), rs.getInt("id_cliente"), id_veterinario, rs.getInt("id_mascota"));
                turnos.add(turno);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return turnos;
    }
}

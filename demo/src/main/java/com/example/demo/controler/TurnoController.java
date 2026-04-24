package com.example.demo.controler;


import com.example.demo.dao.TurnoDao;
import com.example.demo.model.Turno;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {
    private final TurnoDao turnoDao;

    public TurnoController(TurnoDao turnoDao) {
        this.turnoDao = turnoDao;
    }

    @GetMapping
    public List<Turno> listarTurnos(){
        return turnoDao.obtenerTurnos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurnoPorId
}

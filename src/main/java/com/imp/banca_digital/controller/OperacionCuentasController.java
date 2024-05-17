package com.imp.banca_digital.controller;

import com.imp.banca_digital.dtos.OperacionCuentaDTO;
import com.imp.banca_digital.service.OperacionCuentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operaciones")
public class OperacionCuentasController {
    @Autowired
    private OperacionCuentasService operacionCuentaService;

    @GetMapping
    public ResponseEntity<List<OperacionCuentaDTO>> listarHistorialCuentas() {
        return ResponseEntity.ok(operacionCuentaService.listarOperaciones());
    }
    @GetMapping("/{cuentaID}")
    public ResponseEntity<List<OperacionCuentaDTO>> listarHistorial_deCuenta(@PathVariable(name = "cuentaID")String id) {
        return ResponseEntity.ok(operacionCuentaService.listarHistorial_porCuenta(id));
    }
}

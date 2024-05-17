package com.imp.banca_digital.controller;

import com.imp.banca_digital.dtos.CreditoDTO;
import com.imp.banca_digital.dtos.CuentaBancariaDTO;
import com.imp.banca_digital.dtos.DebitoDTO;
import com.imp.banca_digital.dtos.TransferenciaRequestDTO;
import com.imp.banca_digital.service.CuentaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaBancariaController {
    @Autowired
    private CuentaBancariaService cuentaBancariaService;

    @GetMapping("/{id}")
    public ResponseEntity<CuentaBancariaDTO> mostrarDatosCuentaBancaria(@PathVariable(name = "id") String cuentaID) {
        return ResponseEntity.ok(cuentaBancariaService.obtenerCuenta_ID(cuentaID));
    }
    @GetMapping
    public ResponseEntity<List<CuentaBancariaDTO>> mostrarCuentasBancarias() {
        return ResponseEntity.ok(cuentaBancariaService.listarCuentasBancarias());
    }
    @PostMapping("/depositar")
    public ResponseEntity<CreditoDTO> realizarDeposito(@RequestBody CreditoDTO creditoDTO) {
        cuentaBancariaService.depositar(creditoDTO.getMonto(),creditoDTO.getCuentaID());
        return ResponseEntity.ok(creditoDTO);
    }
    @PostMapping("/retirar")
    public ResponseEntity<DebitoDTO> realizarRetiro(@RequestBody DebitoDTO debitoDTO) {
        cuentaBancariaService.retirar(debitoDTO.getMonto(),debitoDTO.getCuentaID());
        return ResponseEntity.ok(debitoDTO);
    }
    @PostMapping("/transferencia")
    public ResponseEntity<String> realizarTransferencia(@RequestBody TransferenciaRequestDTO transferenciaRequestDTO) {
        cuentaBancariaService.transferir(transferenciaRequestDTO.getMonto(),transferenciaRequestDTO
                .getPropietario_ID(),transferenciaRequestDTO.getDestinatario_ID());
        return ResponseEntity.ok("La transferencia se a realizado con exito");
    }
}

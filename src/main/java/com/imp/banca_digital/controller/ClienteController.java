package com.imp.banca_digital.controller;

import com.imp.banca_digital.dtos.ClienteDTO;
import com.imp.banca_digital.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> listarClientes() {
        return clienteService.listarClientes();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> mostrarCliente_ID(@PathVariable(name = "id")Long id) {
        return ResponseEntity.ok(clienteService.buscarCliente_ID(id));
    }
    @PostMapping
    public ResponseEntity<ClienteDTO> guardarCliente(@RequestBody ClienteDTO clienteDTO) {
        return new ResponseEntity<>(clienteService.crearCliente(clienteDTO), HttpStatus.CREATED);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id,@RequestBody ClienteDTO clienteDTO) {
        return new ResponseEntity<>(clienteService.editarCliente(id,clienteDTO),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.eliminarCliente(id));
    }
    @GetMapping("/search")
    public ResponseEntity<List<ClienteDTO>> buscarClientes_porNombre(@RequestParam(name = "clienteName")
                                                                     String name) {
        return ResponseEntity.ok(clienteService.listaClientes_Nombre(name));
    }
}
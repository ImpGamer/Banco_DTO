package com.imp.banca_digital.service;

import com.imp.banca_digital.dtos.ClienteDTO;
import com.imp.banca_digital.entity.Cliente;
import com.imp.banca_digital.exceptions.ClientNotFoundException;
import com.imp.banca_digital.mappers.CuentaBancariaMapperImpl;
import com.imp.banca_digital.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CuentaBancariaMapperImpl cuentaBancariaMapper;
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    public List<ClienteDTO> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> cuentaBancariaMapper.mapearCliente_a_DTO(cliente))
                .toList();
    }

    public ClienteDTO editarCliente(Long clienteID,ClienteDTO clienteDTO)throws ClientNotFoundException {
        Optional<Cliente> clienteBBDD = clienteRepository.findById(clienteID);
        if(clienteBBDD.isEmpty()) {
            throw new ClientNotFoundException("El cliente con el ID: "+clienteID+" no se encontro");
        }
        Cliente editCliente = clienteBBDD.get();
        editCliente.setNombre(clienteDTO.getNombre());
        editCliente.setEmail(clienteDTO.getEmail());
        return cuentaBancariaMapper.mapearCliente_a_DTO(clienteRepository.save(editCliente));
    }
    public String eliminarCliente(Long id)throws ClientNotFoundException {
        Optional<Cliente> clienteDelete = clienteRepository.findById(id);
        if(clienteDelete.isEmpty()) {
            throw new ClientNotFoundException("El cliente a eliminar no fue encontrado");
        }
        clienteRepository.delete(clienteDelete.get());
        return "El cliente "+clienteDelete.get().getNombre()+" fue eliminado correctamente";
    }

    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        Cliente clienteSave = cuentaBancariaMapper.mapearDTO_a_Cliente(clienteDTO);
        clienteSave = clienteRepository.save(clienteSave);
        return cuentaBancariaMapper.mapearCliente_a_DTO(clienteSave);
    }
    public ClienteDTO buscarCliente_ID(Long id)throws ClientNotFoundException {
        Optional<Cliente> clienteBBDD = clienteRepository.findById(id);
        if(clienteBBDD.isEmpty()) {throw new ClientNotFoundException("No se a encontrado el cliente");}
        //Se convierte a un objeto DTO
        return cuentaBancariaMapper.mapearCliente_a_DTO(clienteBBDD.get());
    }
    public List<ClienteDTO> listaClientes_Nombre(String clienteName)throws ClientNotFoundException {
        List<Cliente> clientesNombre = clienteRepository.searchClientes(clienteName);
        if(clientesNombre.isEmpty()) {
            throw new ClientNotFoundException("No se han encontrado clientes con el nombre: "+clienteName);
        }
        return clientesNombre.stream()
                .map(cliente -> cuentaBancariaMapper.mapearCliente_a_DTO(cliente))
                .toList();
    }
}
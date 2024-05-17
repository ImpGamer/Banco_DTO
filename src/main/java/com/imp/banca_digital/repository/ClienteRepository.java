package com.imp.banca_digital.repository;

import com.imp.banca_digital.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    @Query(value = "SELECT c FROM Cliente c WHERE c.nombre LIKE %?1%")
    List<Cliente> searchClientes(String clientName);
}
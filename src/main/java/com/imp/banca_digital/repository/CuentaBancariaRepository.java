package com.imp.banca_digital.repository;

import com.imp.banca_digital.entity.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria,String> {
    @Query(value = "SELECT * FROM cuenta_bancaria WHERE cliente_id = ?1",nativeQuery = true)
    List<CuentaBancaria> findCuentaBancariaByClienteId(Long clienteID);
}

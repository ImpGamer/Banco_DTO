package com.imp.banca_digital.repository;

import com.imp.banca_digital.entity.OperacionCuenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperacionCuentaRepository extends JpaRepository<OperacionCuenta,Long> {
    @Query(value = "SELECT * FROM operacion_cuenta WHERE cuenta_bancaria_id = ?1",nativeQuery = true)
    List<OperacionCuenta> findOperacionesByCuentaID(String cuentaId);

}
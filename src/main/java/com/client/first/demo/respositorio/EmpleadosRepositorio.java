package com.client.first.demo.respositorio;

import com.client.first.demo.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadosRepositorio extends JpaRepository<Empleado, Long> {
}

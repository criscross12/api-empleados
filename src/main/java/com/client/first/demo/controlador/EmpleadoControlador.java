package com.client.first.demo.controlador;

import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.client.first.demo.model.Empleado;
import com.client.first.demo.respositorio.EmpleadosRepositorio;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class EmpleadoControlador {
	@Autowired
	private EmpleadosRepositorio repositorio;

	// End-point para listar todos los empleados
	@GetMapping("/empleados")
	public List<Empleado> listarTodos() {
		return repositorio.findAll();
	}

	// End-point para guardar empleado
	@PostMapping("/empleados")
	public Empleado saveEmpleado(@RequestBody Empleado empleado) {
		System.out.println(empleado);
		return repositorio.save(empleado);
	}

	// End-point para actualizar empleado
	@PutMapping("/empleados/{id}")
	public ResponseEntity<Empleado> updateEmpleado(@PathVariable Long id, @RequestBody Empleado updateEmpleado) {
		Empleado empleado = repositorio.findById(id).orElseThrow(() -> new ResolutionException("No se encontro o no existe el usuario"));
		empleado.setNombre(updateEmpleado.getNombre());
		empleado.setApellido(updateEmpleado.getApellido());
		empleado.setEmail(updateEmpleado.getEmail());
		Empleado empleadoUpdate = repositorio.save(empleado);
		return ResponseEntity.ok(empleadoUpdate);
	}

	// End-point para eliminar empleado
	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmpleado(@PathVariable Long id) {
		Empleado empleado = repositorio.findById(id).orElseThrow(() -> new ResolutionException("No se encontro o no existe el usuario"));
		repositorio.delete(empleado);
		Map<String,Boolean> res = new HashMap<>();
		res.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(res);
	}

}

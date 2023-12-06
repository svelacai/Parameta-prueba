package com.parameta.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.parameta.prueba.DTO.EmpleadoDTO;
import com.parameta.prueba.service.EmpleadoService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/empleado")
@Api(value = "API de Ejemplo", description = "Operaciones relacionadas con la API de ejemplo")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
public class EmpleadoController {

	@Autowired
	EmpleadoService empleadoServic;

	@GetMapping("/createClien")
	public ResponseEntity<?> consultar(@RequestBody EmpleadoDTO empleadoDTO) throws JsonProcessingException {

		String consulta = empleadoServic.createEmpleado(empleadoDTO);

		return new ResponseEntity<>(consulta, HttpStatus.OK);
	}

}

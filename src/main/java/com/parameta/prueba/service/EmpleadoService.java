package com.parameta.prueba.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parameta.prueba.DTO.EmpleadoDTO;
import com.parameta.prueba.entity.Empleado;
import com.parameta.prueba.mapper.EmpleadoMapper;

@Service
public class EmpleadoService {

	@Autowired
	SoapServiceClient client;

	public String createEmpleado(EmpleadoDTO empleado) throws JsonProcessingException {
		String mensaje;
		try {
			if (empleado.getFechaNacimiento() == null || empleado.getFechaVinculacion() == null) {
				return "Error: Las fechas no pueden ser nulas.";
			}

			if (!validarFormatoFecha(empleado.getFechaNacimiento())
					|| !validarFormatoFecha(empleado.getFechaVinculacion())) {
				return "Error: Formato de fecha inválido.";
			}

			if (!mayorDeEdad(empleado.getFechaNacimiento())) {
				return "Error: El empleado debe ser mayor de 18 años.";
			}

			Period tiempoVinculacion = calcularTiempoVinculacion(empleado.getFechaVinculacion());

			Period edadActual = calcularEdadActual(empleado.getFechaNacimiento());
			Empleado request = EmpleadoMapper.toEntity(empleado);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			client.guardarEmpleado(request);
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode jsonResponse = objectMapper.createObjectNode();
			String jsonString = gson.toJson(empleado);

			jsonResponse.put("tiempoVinculacion", formatoPeriodo(tiempoVinculacion));
			jsonResponse.put("edadActual", formatoPeriodo(edadActual));

			return "Resquest:" + jsonString + "\n" + jsonResponse.toString();
		} catch (Exception e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}

	private Period calcularTiempoVinculacion(String fechaVinculacion) {
		LocalDate fechaInicio = convertirAFecha(fechaVinculacion);
		LocalDate fechaActual = LocalDate.now();
		return Period.between(fechaInicio, fechaActual);
	}

	private Period calcularEdadActual(String fechaNacimiento) {
		LocalDate fechaNac = convertirAFecha(fechaNacimiento);
		LocalDate fechaActual = LocalDate.now();
		return Period.between(fechaNac, fechaActual);
	}

	private LocalDate convertirAFecha(String fecha) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(fecha).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
		} catch (ParseException e) {
			throw new IllegalArgumentException("Formato de fecha inválido.", e);
		}
	}

	private boolean validarFormatoFecha(String fecha) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		try {
			dateFormat.parse(fecha);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	private boolean mayorDeEdad(String fechaNacimiento) {
		LocalDate fechaNac = convertirAFecha(fechaNacimiento);
		LocalDate fecha18 = LocalDate.now().minusYears(18);
		return !fechaNac.isAfter(fecha18);
	}

	private String formatoPeriodo(Period period) {
		return period.getYears() + " años, " + period.getMonths() + " meses, " + period.getDays() + " días";
	}
}

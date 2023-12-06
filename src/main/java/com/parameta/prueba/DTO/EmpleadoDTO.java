package com.parameta.prueba.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EmpleadoDTO {

	private String nombres;
	private String apellidos;
	private String tipoDocumento;
	private String numeroDocumento;
	private String fechaNacimiento;
	private String fechaVinculacion;
	private String cargo;
	private double salario;

}

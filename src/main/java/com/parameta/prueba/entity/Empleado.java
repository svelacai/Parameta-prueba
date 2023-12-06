package com.parameta.prueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Empleado", schema = "prueba")
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombres", nullable = false)
	private String nombres;

	@Column(name = "apellidos", nullable = false)
	private String apellidos;

	@Column(name = "tipo_documento", nullable = false)
	private String tipoDocumento;

	@Column(name = "numero_documento", nullable = false)
	private String numeroDocumento;

	@Column(name = "fecha_nacimiento", nullable = false)
	private String fechaNacimiento;

	@Column(name = "fecha_vinculacion", nullable = false)
	private String fechaVinculacion;

	@Column(name = "cargo", nullable = false)
	private String cargo;

	@Column(name = "salario", nullable = false)
	private double salario;
}

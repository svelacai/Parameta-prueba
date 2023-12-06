package com.parameta.prueba.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.parameta.prueba.DTO.EmpleadoDTO;
import com.parameta.prueba.entity.Empleado;

public class EmpleadoMapper {

	public static EmpleadoDTO toDTO(Empleado empleado) {
		EmpleadoDTO empleadodto = new EmpleadoDTO();
		empleadodto.setNombres(empleado.getNombres());
		empleadodto.setApellidos(empleado.getApellidos());
		empleadodto.setTipoDocumento(empleado.getTipoDocumento());
		empleadodto.setNumeroDocumento(empleado.getNumeroDocumento());
		empleadodto.setFechaNacimiento(empleado.getFechaNacimiento());
		empleadodto.setFechaVinculacion(empleado.getFechaVinculacion());
		empleadodto.setCargo(empleado.getCargo());
		empleadodto.setSalario(empleado.getSalario());
		return empleadodto;

	}

	public static Empleado toEntity(EmpleadoDTO empleadoDTO) {
		Empleado empleado = new Empleado();
		empleado.setNombres(empleadoDTO.getNombres());
		empleado.setApellidos(empleadoDTO.getApellidos());
		empleado.setTipoDocumento(empleadoDTO.getTipoDocumento());
		empleado.setNumeroDocumento(empleadoDTO.getNumeroDocumento());
		empleado.setFechaNacimiento(empleadoDTO.getFechaNacimiento());
		empleado.setFechaVinculacion(empleadoDTO.getFechaVinculacion());
		empleado.setCargo(empleadoDTO.getCargo());
		empleado.setSalario(empleadoDTO.getSalario());
		return empleado;
	}

	public static List<EmpleadoDTO> toDTOList(List<Empleado> empleados) {
		return empleados.stream().map(EmpleadoMapper::toDTO).collect(Collectors.toList());
	}

	public static List<Empleado> toEntityList(List<EmpleadoDTO> empleadoDTOList) {
		return empleadoDTOList.stream().map(EmpleadoMapper::toEntity).collect(Collectors.toList());
	}
}

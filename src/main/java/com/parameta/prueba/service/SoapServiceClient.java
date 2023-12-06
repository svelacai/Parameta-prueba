package com.parameta.prueba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parameta.prueba.entity.Empleado;
import com.parameta.prueba.feing.SoapClient;

@Service
public class SoapServiceClient {

	private final SoapClient soapClient;

	@Autowired
	public SoapServiceClient(SoapClient soapClient) {
		this.soapClient = soapClient;
	}

	public String guardarEmpleado(Empleado empleado) {
		String soapRequest = convertirEmpleadoASoapRequest(empleado);

		return soapClient.guardarEmpleado(soapRequest);
	}

	private String convertirEmpleadoASoapRequest(Empleado empleado) {
	    StringBuilder soapRequestBuilder = new StringBuilder();
	    soapRequestBuilder.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" ");
	    soapRequestBuilder.append("xmlns:ser=\"http://service.Parameta.com/\">");
	    soapRequestBuilder.append("<soapenv:Header/>");
	    soapRequestBuilder.append("<soapenv:Body>");
	    soapRequestBuilder.append("<ser:guardarEmpleado>");

	    soapRequestBuilder.append("<empleado>");
	    soapRequestBuilder.append("<nombres>").append(empleado.getNombres()).append("</nombres>");
	    soapRequestBuilder.append("<apellidos>").append(empleado.getApellidos()).append("</apellidos>");
	    soapRequestBuilder.append("<tipoDocumento>").append(empleado.getTipoDocumento()).append("</tipoDocumento>");
	    soapRequestBuilder.append("<numeroDocumento>").append(empleado.getNumeroDocumento()).append("</numeroDocumento>");
	    soapRequestBuilder.append("<fechaNacimiento>").append(empleado.getFechaNacimiento()).append("</fechaNacimiento>");
	    soapRequestBuilder.append("<fechaVinculacion>").append(empleado.getFechaVinculacion()).append("</fechaVinculacion>");
	    soapRequestBuilder.append("<cargo>").append(empleado.getCargo()).append("</cargo>");
	    soapRequestBuilder.append("<salario>").append(empleado.getSalario()).append("</salario>");
	    soapRequestBuilder.append("</empleado>");

	    soapRequestBuilder.append("</ser:guardarEmpleado>");
	    soapRequestBuilder.append("</soapenv:Body>");
	    soapRequestBuilder.append("</soapenv:Envelope>");

	    return soapRequestBuilder.toString();
	}


}

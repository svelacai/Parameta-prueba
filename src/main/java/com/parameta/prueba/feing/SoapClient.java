package com.parameta.prueba.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "servisoap", url ="http://localhost:8080")
public interface SoapClient {

    @PostMapping("services/empleadoService")
    String guardarEmpleado(@RequestBody String soapRequest);
}

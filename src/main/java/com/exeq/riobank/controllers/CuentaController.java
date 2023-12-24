package com.exeq.riobank.controllers;

import com.exeq.riobank.DTOs.CuentaDTO;
import com.exeq.riobank.mappers.CuentaMapper;
import com.exeq.riobank.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CuentaController {

  @Autowired
  private CuentaService cuentaService;
  @Autowired
  private CuentaMapper cuentaMapper;

  @GetMapping("/cuentas")
  public List<CuentaDTO> listadoCuentas(){
    return cuentaMapper.transformarAListaCuentaDTO(cuentaService.listadoCuentas());
  }

  @GetMapping("/cuentas/{id}")
  public CuentaDTO cuentaId(@PathVariable Long id){
    return cuentaMapper.transformarACuentaDTO(cuentaService.cuentaId(id));
  }
}

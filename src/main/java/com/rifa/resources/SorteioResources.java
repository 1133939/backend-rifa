package com.rifa.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rifa.model.Sorteio;
import com.rifa.service.SorteioService;

@RestController
@RequestMapping(value="/sorteios")
public class SorteioResources {
@Autowired
private SorteioService service;

@RequestMapping(method=RequestMethod.GET)
public ResponseEntity<?> findAll(){
List<Sorteio>sorteioList = service.findAll();
return ResponseEntity.ok().body(sorteioList);
}
}

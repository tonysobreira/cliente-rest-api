package com.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.model.Cliente;
import com.rest.service.ClienteService;

//@CrossOrigin(origins = "*")
//@RestController("clienteRestController")
public class ClienteRestController2 {

//	@Autowired
//	private ClienteService clienteService;
//
//	@GetMapping(value = "/cliente")
//	public List<Cliente> findAllClientes() {
//		return clienteService.findAllClientes();
//	}
//
//	@GetMapping(value = "/cliente/{id}")
//	public Cliente findClienteById(@PathVariable("id") Integer id) {
//		return clienteService.findClienteById(id);
//	}
//
//	@PostMapping(value = "/cliente")
//	public Cliente saveCliente(@RequestBody Cliente cliente) {
//		return clienteService.saveOrUpdateCliente(cliente);
//	}
//
//	@PutMapping(value = "/cliente/{id}")
//	public Cliente updateCliente(@PathVariable("id") Integer id, @RequestBody Cliente cliente) {
//		cliente.setId(id);
//		return clienteService.saveOrUpdateCliente(cliente);
//	}
//
//	@DeleteMapping(value = "/cliente/{id}")
//	public void deleteClienteById(@PathVariable("id") Integer id) {
//		clienteService.deleteClienteById(id);
//	}
//
//	@GetMapping(value = "/cliente-cpf/{cpf}")
//	public Cliente findClienteByCpf(@PathVariable("cpf") String cpf) {
//		return clienteService.findClienteByCpf(cpf);
//	}

}

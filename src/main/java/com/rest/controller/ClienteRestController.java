package com.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@CrossOrigin(origins = "*")
@RestController("clienteRestController")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping(value = "/cliente")
	public ResponseEntity<List<Cliente>> findAllClientes() {
		List<Cliente> clienteList = clienteService.findAllClientes();
		
		if (clienteList.isEmpty()) {
			return new ResponseEntity<List<Cliente>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}

		return new ResponseEntity<List<Cliente>>(clienteList, HttpStatus.OK);
	}

	@GetMapping(value = "/cliente/{id}")
	public ResponseEntity<?> findClienteById(@PathVariable("id") Integer id) {
		Cliente cliente = clienteService.findClienteById(id);
		
		if (cliente == null) {
			return new ResponseEntity("Cliente com id " + id + " não encontrado.", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@PostMapping(value = "/cliente")
	public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente) {
		Cliente c = clienteService.findClienteByCpf(cliente.getCpf());
		
		if (c != null) {
			return new ResponseEntity("Um cliente com cpf " + cliente.getCpf() + " já existe.", HttpStatus.CONFLICT);
		}

		Cliente createdCliente = clienteService.saveOrUpdateCliente(cliente);

		return new ResponseEntity<Cliente>(createdCliente, HttpStatus.CREATED);
	}

	@PutMapping(value = "/cliente/{id}")
	public ResponseEntity<?> updateCliente(@PathVariable("id") Integer id, @RequestBody Cliente cliente) {
		Cliente c1 = clienteService.findClienteById(id);

		if (c1 == null) {
			return new ResponseEntity("Cliente com id " + id + " não encontrado.", HttpStatus.NOT_FOUND);
		}

		if (!c1.getCpf().equals(cliente.getCpf())) {
			Cliente c2 = clienteService.findClienteByCpf(cliente.getCpf());

			if (c2 != null) {
				return new ResponseEntity("Um Cliente com cpf " + cliente.getCpf() + " já existe.",	HttpStatus.CONFLICT);
			}
		}
		
		c1.setNome(cliente.getNome());
		c1.setCpf(cliente.getCpf());
		c1.setDataNascimento(cliente.getDataNascimento());
		c1.setTelefone(cliente.getTelefone());
		c1.setEmail(cliente.getEmail());
		c1.setEndereco(cliente.getEndereco());
		c1.setCep(cliente.getCep());
		c1.setBairro(cliente.getBairro());
		c1.setComplemento(cliente.getComplemento());
		c1.setCidade(cliente.getCidade());
		c1.setEstado(cliente.getEstado());

		Cliente updatedCliente = clienteService.saveOrUpdateCliente(cliente);

		return new ResponseEntity<Cliente>(updatedCliente, HttpStatus.OK);
	}

	@DeleteMapping(value = "/cliente/{id}")
	public ResponseEntity<?> deleteClienteById(@PathVariable("id") Integer id) {
		Cliente c = clienteService.findClienteById(id);

		if (c == null) {
			return new ResponseEntity("Cliente com id " + id + " não encontrado.", HttpStatus.NOT_FOUND);
		}

		clienteService.deleteClienteById(id);
		return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/cliente-cpf/{cpf}")
	public ResponseEntity<?> findClienteByCpf(@PathVariable("cpf") String cpf) {
		Cliente cliente = clienteService.findClienteByCpf(cpf);

		if (cliente == null) {
			return new ResponseEntity("Cliente com cpf " + cpf + " não encontrado.", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

}

package com.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rest.model.Cliente;
import com.rest.service.ClienteService;

@CrossOrigin(origins = "*")
@RestController("clienteRestController")
public class ClienteRestController {

	public static final Logger logger = LoggerFactory.getLogger(ClienteRestController.class);

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/clientes", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<List<Cliente>> findAllClientes() {
		List<Cliente> clienteList = clienteService.findAllClientes();

		if (clienteList.isEmpty()) {
			return new ResponseEntity<List<Cliente>>(HttpStatus.NO_CONTENT);
			//return new ResponseEntity<List<Cliente>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Cliente>>(clienteList, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/cliente/{id}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		logger.info("Buscando Cliente com id {}", id);

		Cliente cliente = clienteService.findClienteById(id);

		if (cliente == null) {
			logger.error("Cliente com id {} não encontrado.", id);
			return new ResponseEntity("Cliente com id " + id + " não encontrado.", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/cliente", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente, UriComponentsBuilder ucBuilder) {
		logger.info("Salvando Cliente : {}", cliente);

		Cliente c = clienteService.findClienteByCpf(cliente.getCpf());

		if (c != null) {
			logger.error("Um Cliente com cpf {} já existe.", cliente.getCpf());

			return new ResponseEntity("Um Cliente com cpf " + cliente.getCpf() + " já existe.", HttpStatus.CONFLICT);
		}

		c = clienteService.saveOrUpdateCliente(cliente);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri());

		return new ResponseEntity<Cliente>(c, HttpStatus.CREATED);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/cliente/{id}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<?> updateCliente(@PathVariable("id") Integer id, @RequestBody Cliente cliente) {
		logger.info("Atualizando Cliente com id {}", id);

		Cliente c1 = clienteService.findClienteById(id);

		if (c1 == null) {
			logger.error("Cliente com id {} não encontrado.", id);
			return new ResponseEntity("Cliente com id " + id + " não encontrado.", HttpStatus.NOT_FOUND);
		}

		if (!c1.getCpf().equals(cliente.getCpf())) {
			Cliente c2 = clienteService.findClienteByCpf(cliente.getCpf());

			if (c2 != null) {
				logger.error("Um Cliente com cpf {} já existe.", cliente.getCpf());

				return new ResponseEntity("Um Cliente com cpf " + cliente.getCpf() + " já existe.", HttpStatus.CONFLICT);
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

		Cliente c3 = clienteService.saveOrUpdateCliente(cliente);

		return new ResponseEntity<Cliente>(c3, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/cliente/{id}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<?> deleteClienteById(@PathVariable("id") Integer id) {
		logger.info("Buscando e Deletando Cliente com id {}", id);

		Cliente c = clienteService.findClienteById(id);

		if (c == null) {
			logger.error("Cliente com id {} não encontrado.", id);
			return new ResponseEntity("Cliente com id " + id + " não encontrado.", HttpStatus.NOT_FOUND);
		}

		clienteService.deleteClienteById(id);
		return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/cliente", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<?> deleteCliente(@RequestBody Cliente cliente) {
		logger.info("Buscando e Deletando Cliente {}", cliente);

		Cliente c = clienteService.findClienteById(cliente.getId());

		if (c == null) {
			logger.error("Cliente {} não encontrado.", cliente);
			return new ResponseEntity("Cliente " + cliente + " não encontrado.", HttpStatus.NOT_FOUND);
		}

		clienteService.deleteCliente(c);
		return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/findclientebycpf/{cpf}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<?> findClienteByCpf(@PathVariable("cpf") String cpf) {
		logger.info("Buscando Cliente com cpf {}", cpf);

		Cliente cliente = clienteService.findClienteByCpf(cpf);

		if (cliente == null) {
			logger.error("Cliente com cpf {} não encontrado.", cpf);
			return new ResponseEntity("Cliente com cpf " + cpf + " não encontrado.", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

}

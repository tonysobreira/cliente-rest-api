package com.rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.model.Cliente;
import com.rest.repository.ClienteRepository;
import com.rest.service.ClienteService;

@Service("clienteService")
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> findAllClientes() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente saveOrUpdateCliente(Cliente client) {
		return clienteRepository.save(client);
	}

	@Override
	public Cliente findClienteById(Integer id) {
		Optional<Cliente> optionalClient = clienteRepository.findById(id);

		if (optionalClient.isPresent()) {
			return optionalClient.get();
		} else {
			return null;
		}

	}

	@Override
	public void deleteClienteById(Integer id) {
		clienteRepository.deleteById(id);
	}

	@Override
	public void deleteCliente(Cliente client) {
		clienteRepository.delete(client);
	}

	@Override
	public Cliente findClienteByCpf(String cpf) {
		return clienteRepository.findByCpf(cpf);
	}

}

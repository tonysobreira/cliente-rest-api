package com.rest.service;

import java.util.List;

import com.rest.model.Cliente;

public interface ClienteService {

	public List<Cliente> findAllClientes();

	public Cliente findClienteById(Integer id);

	public Cliente saveOrUpdateCliente(Cliente data);

	public void deleteClienteById(Integer id);

	public Cliente findClienteByCpf(String cpf);

}

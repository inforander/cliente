package com.sherwinwilliams.cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sherwinwilliams.cliente.model.Cliente;
import com.sherwinwilliams.cliente.model.StatusCliente;
import com.sherwinwilliams.cliente.repository.Clientes;
import com.sherwinwilliams.cliente.repository.filter.ClienteFilter;

@Service
public class CadastroClienteService {

	@Autowired
	private Clientes clientes;

	public void salvar(Cliente cliente) {
		clientes.save(cliente);
	}

	public String ativar(Long codigo) {
		Cliente cliente = clientes.findOne(codigo);
		cliente.setStatus(StatusCliente.ATIVO);
		clientes.save(cliente);

		return StatusCliente.ATIVO.getDescricao();
	}

	public List<Cliente> filtrar(ClienteFilter filtro) {
		String razaoSocial = filtro.getRazaoSocial() == null ? "%" : filtro.getRazaoSocial();
		return clientes.findByRazaoSocialContaining(razaoSocial);
	}

}

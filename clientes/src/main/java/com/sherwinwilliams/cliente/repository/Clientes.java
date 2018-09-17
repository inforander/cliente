package com.sherwinwilliams.cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sherwinwilliams.cliente.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long> {

	public List<Cliente> findByRazaoSocialContaining(String razaoSocial);

}
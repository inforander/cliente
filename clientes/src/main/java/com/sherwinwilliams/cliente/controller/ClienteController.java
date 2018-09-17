package com.sherwinwilliams.cliente.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sherwinwilliams.cliente.model.Cliente;
import com.sherwinwilliams.cliente.model.StatusCliente;
import com.sherwinwilliams.cliente.repository.filter.ClienteFilter;
import com.sherwinwilliams.cliente.service.CadastroClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	private static final String PESQUISA_VIEW = "PesquisaClientes";

	private static final String CADASTRO_VIEW = "CadastroCliente";

	@Autowired
	private CadastroClienteService cadastroClienteService;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Cliente());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Cliente cliente, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}

		cadastroClienteService.salvar(cliente);
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
		return "redirect:/clientes/novo";

	}

	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") ClienteFilter filtro) {
		List<Cliente> todosClientes = cadastroClienteService.filtrar(filtro);

		ModelAndView mv = new ModelAndView(PESQUISA_VIEW);
		mv.addObject("clientes", todosClientes);
		return mv;
	}

	@RequestMapping(value = "/{codigo}/ativar", method = RequestMethod.PUT)
	public @ResponseBody String ativar(@PathVariable Long codigo) {
		return cadastroClienteService.ativar(codigo);
	}

	@ModelAttribute("todosStatusCliente")
	public List<StatusCliente> todosStatusCliente() {
		return Arrays.asList(StatusCliente.values());
	}

}
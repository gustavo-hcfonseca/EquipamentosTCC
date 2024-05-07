package com.fourcatsdev.aula20.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fourcatsdev.aula20.modelo.Pedido;
import com.fourcatsdev.aula20.service.EquipamentoService;
import com.fourcatsdev.aula20.service.PedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private EquipamentoService equipamentoService;
	
	@RequestMapping("/listar")
	public String listarPedido(Model model) {
		List<Pedido> pedidos = pedidoService.listar();
 		model.addAttribute("pedidos", pedidos);		
		return "/auth/admin/admin-listar-pedido";
	}
	
	@GetMapping("/apagar/{id}")
	public String deletePedido(@PathVariable("id") long id, Model model) {
		pedidoService.apagarPedidoPorId(id);				
	    return "redirect:/auth/admin/pedido/listar";
	}
	
	//implementar
	/*
	@RequestMapping("/ver")
	public String VerPedido(Model model) {
		List<Equipamentos> equipamentos = equipamentoService.listar();//fazer o relacionamento many to many para conseguir listar
 		model.addAttribute("pedidos", pedidos);		
		return "/auth/admin/admin-ver-pedido";
	}
	*/
	
	//alterar para o pedido ser feito com as informações preenchidas pelo usuario na user-listar-equipamento 
	/*@GetMapping("/novo")
    public String novoPedido(Model model) {	
		Pedido pedido = new Pedido();
		model.addAttribute("pedido",pedido);
        return "/auth/admin/admin-novo-pedido";
    }
	*/
	/*
	@PostMapping("/gravar")
	public String gravarPedido(@ModelAttribute("pedido") @Valid Pedido pedido,
			BindingResult erros,
			RedirectAttributes attributes) {
		if(erros.hasErrors()) {
			return "/auth/admin/admin-novo-pedido";
		}
		pedidoService.gravar(pedido);
		attributes.addFlashAttribute("mensagem", "Pedido salva com sucesso!");
        return "redirect:/auth/admin/pedido/listar";
    }
	*/
	//verificar se editar será usado
	/*
	@GetMapping("/editar/{id}")
	public String editarPedido(@PathVariable("id") long id, Model model) {
		Pedido pedido = pedidoService.buscarPedidoPorId(id);
	    model.addAttribute("pedido", pedido);	    
	    return "/auth/admin/admin-alterar-pedido";
	}

	@PostMapping("/editar/{id}")
	public String editarPedido(@PathVariable("id") long id, @Valid Pedido pedido, BindingResult result) {
		if (result.hasErrors()) {
	    	pedido.setId(id);
	        return "/auth/admin/admin-alterar-pedido";
	    }
		pedidoService.alterarPedido(pedido);
	    return "redirect:/auth/admin/pedido/listar";
	}
	*/
}

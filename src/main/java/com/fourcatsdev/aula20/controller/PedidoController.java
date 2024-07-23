package com.fourcatsdev.aula20.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fourcatsdev.aula20.modelo.Equipamento;
import com.fourcatsdev.aula20.modelo.Pedido;
import com.fourcatsdev.aula20.modelo.Usuario;
import com.fourcatsdev.aula20.repository.PedidoResponseData;
import com.fourcatsdev.aula20.repository.PedidoResponseEquipamento;
import com.fourcatsdev.aula20.service.EquipamentoService;
import com.fourcatsdev.aula20.service.PedidoService;
import com.fourcatsdev.aula20.service.UsuarioService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private EquipamentoService equipamentoService;
	
	
	
	/*
	 * 
	 * método alterarEstado recebe o id do pedido e o id
	 * pedidoBuscado =  vai no banco busca o pedido por id
	 * se for aprovado 
	 * estadoPedidoBuscado = vai no banco busca EstadoPedido pelo id de aprovado
	 *   pedidoBuscado.setEstadoPedido(estadoPedidoBuscado )
	 *   salvar(pedidoBuscado)
	 * 
	 * */
	
	//listar para o admin
	@RequestMapping("/admin/listar")
	public String listarPedidoAdmin(Model model) {
		List<PedidoResponseData> pedidos = 	pedidoService.buscarPedido();
		model.addAttribute("pedidos", pedidos);
		for(PedidoResponseData p:pedidos) {
			System.out.println(p.getId());
			System.out.println(p.getData());
		}
		return "/auth/admin/admin-listar-pedido";
	}
	
	//listar para o user(listar pedidos apenas do usuário)
	 
	@RequestMapping("/listar")
	public String listarPedido(Model model, @CurrentSecurityContext(expression = "authentication.name") String login){
	   
		Usuario usuario = usuarioService.buscarUsuarioPorLogin(login);
		// Buscando o pedido pelo ID
		List<PedidoResponseData> pedidos = pedidoService.buscarPedidoDeUsuario(usuario.getId());
		
	    // Adicionando o pedido ao modelo
	    model.addAttribute("pedido", pedidos);
	    for(PedidoResponseData p:pedidos) {
			System.out.println(p.getId());
			System.out.println(p.getData());
		}
	    return "/auth/user/user-listar-pedido";
	}
	
	
	@GetMapping("/apagar/{id}")
	public String deletePedido(@PathVariable("id") long id, Model model) {
		pedidoService.apagarPedidoPorId(id);				
	    return "redirect:/auth/admin/pedido/listar";
	}
	
	
	
	
	@RequestMapping("/ver/{id}/{data}")
	public String VerPedido(@PathVariable("id") Long id, @PathVariable("data") String data, Model model) {
		System.out.println("------------------> "+ data);
		List<PedidoResponseEquipamento> equipamentos = pedidoService.buscarPedidoEquipamento(id, data);
		for(PedidoResponseEquipamento e:equipamentos) {
			System.out.println(e.getId());
			System.out.println(e.getFabricante());
		}
 		model.addAttribute("equipamentos", equipamentos);		
		return "/auth/admin/admin-ver-pedido";
	}


	//implementar um ver pedido para o usuário também
	
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

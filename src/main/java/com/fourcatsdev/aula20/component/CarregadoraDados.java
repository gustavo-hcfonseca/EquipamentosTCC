package com.fourcatsdev.aula20.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.fourcatsdev.aula20.modelo.Estado;
import com.fourcatsdev.aula20.modelo.Papel;
import com.fourcatsdev.aula20.repository.PapelRepository;
import com.fourcatsdev.aula20.service.EstadoService;

@Component
public class CarregadoraDados implements CommandLineRunner {

	@Autowired
	private PapelRepository papelRepository;
	
	
	
	@Autowired
	private EstadoService estadoService;
	
	@Override
	public void run(String... args) throws Exception {
		
		String[] papeis = {"ADMIN", "USER"};
		
		for (String papelString: papeis) {
			Papel papel = papelRepository.findByPapel(papelString);
			if (papel == null) {
				papel = new Papel(papelString);
				papelRepository.save(papel);
			}
		}	
		
		String[] estados = { "Disponível", "Manutenção", "Emprestado" };

		for (String estadoString : estados) {
			Estado estado = estadoService.buscarPorNome(estadoString);
			if (estado == null) {
				estado = new Estado(estadoString);
				estadoService.gravar(estado);
			}
		}
		
	}

}

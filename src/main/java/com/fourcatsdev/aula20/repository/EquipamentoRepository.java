package com.fourcatsdev.aula20.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourcatsdev.aula20.modelo.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
	List<Equipamento> findByNomeOrFabricanteContainingIgnoreCase(String nome,String fabricante);

}

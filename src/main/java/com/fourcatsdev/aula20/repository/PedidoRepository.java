package com.fourcatsdev.aula20.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourcatsdev.aula20.modelo.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}

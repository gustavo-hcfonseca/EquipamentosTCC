package com.fourcatsdev.aula20.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fourcatsdev.aula20.modelo.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query(value = "select distinct usuario_id as id, CAST(data_pedido AS date) as data, nome \n"
			     + "  from pedido \n"
			     + "  join usuario \n"
		         + "    on usuario.id = pedido.usuario_id", nativeQuery = true)
	List<PedidoResponseData> buscarPedidoDeUsuario();
	
	@Query(value = "select p.id, p.data_devolucao as devolucao, p.data_emprestimo as emprestimo, e.nome, e.numero_de_serie as numero, e.fabricante \n"
		     	+ "  from pedido p, equipamento e \n"
		    	+ " where p.usuario_id = :idPedido \n"
		    	+ "   and CAST(p.data_pedido AS date) = cast(:dataPedido as date)", nativeQuery = true)
    List<PedidoResponseEquipamento> buscarPedidoEquipamento(@Param("idPedido") Long idPedido, @Param("dataPedido") String data);

}

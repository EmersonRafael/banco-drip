package com.drip.banco.repository;

import com.drip.banco.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia,Long> {
   public List<Transferencia> findByContaOrigemId(Long idContaOrigem);
   @Query("select t from Transferencia t where t.contaOrigem.id = ?1 and t.contaOrigem.banco.id <> t.contaDestino.banco.id and t.dataCriacao BETWEEN ?2 and ?3")
   public List<Transferencia> findByTransferenciasParaContasExternas(Long idContaOrigem, LocalDateTime dataInicio, LocalDateTime dataFim);
}

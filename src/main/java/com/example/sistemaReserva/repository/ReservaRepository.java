package com.example.sistemaReserva.repository;

import com.example.sistemaReserva.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByItemNome(String nome);

    // Se você quiser buscar uma reserva específica por item e data:
    List<Reserva> findByItemNomeAndDataHora(String nome, LocalDateTime dataHora);
}

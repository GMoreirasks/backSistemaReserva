package com.example.sistemaReserva.repository;

import com.example.sistemaReserva.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByItemNome(String nome);
}

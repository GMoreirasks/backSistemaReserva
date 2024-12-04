package com.example.sistemaReserva.repository;

import com.example.sistemaReserva.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByNome(String nome); // Retorna Optional para evitar NPE
    List<Item> findByNomeContainingIgnoreCase(String nome);
}

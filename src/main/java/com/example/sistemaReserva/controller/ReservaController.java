package com.example.sistemaReserva.controller;

import com.example.sistemaReserva.model.Reserva;
import com.example.sistemaReserva.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    // Listar todas as reservas
    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

    // Criar uma nova reserva
    @PostMapping
    public ResponseEntity<Object> criarReserva(@RequestBody Reserva reserva) {
        if (reserva.getItem() == null || reserva.getItem().getNome() == null) {
            return ResponseEntity.badRequest().body("Item e nome do item são obrigatórios");
        }

        // Salvando a reserva no banco de dados
        Reserva reservaSalva = reservaRepository.save(reserva);
        return ResponseEntity.status(201).body(reservaSalva); // Código de status 201 para criação
    }

    // Obter uma reserva específica pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obterReserva(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        return reserva.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar uma reserva existente
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizarReserva(@PathVariable Long id, @RequestBody Reserva reservaDetalhes) {
        return reservaRepository.findById(id)
                .map(reserva -> {
                    reserva.setItem(reservaDetalhes.getItem());
                    reserva.setDataHora(reservaDetalhes.getDataHora());
                    reserva.setNomeUsuario(reservaDetalhes.getNomeUsuario());
                    Reserva reservaAtualizada = reservaRepository.save(reserva);
                    return ResponseEntity.ok(reservaAtualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir uma reserva pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirReserva(@PathVariable Long id) {
        return reservaRepository.findById(id)
                .map(reserva -> {
                    reservaRepository.delete(reserva);
                    return ResponseEntity.noContent().build(); // Código de status 204 para exclusão
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar reservas pelo nome do item
    @GetMapping("/por-nome-item/{nome}")
    public ResponseEntity<List<Reserva>> listarReservasPorNomeItem(@PathVariable String nome) {
        List<Reserva> reservas = reservaRepository.findByItemNome(nome);
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }
}

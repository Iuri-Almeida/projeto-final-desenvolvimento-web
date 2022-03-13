package br.com.ialmeida.projetofinaldesenvolvimentoweb.repositories;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}

package br.com.ialmeida.projetofinaldesenvolvimentoweb.repositories;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Rebel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebelRepository extends JpaRepository<Rebel, Long> {
}

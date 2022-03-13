package br.com.ialmeida.projetofinaldesenvolvimentoweb.repositories;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Localization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizationRepository extends JpaRepository<Localization, Long> {
}

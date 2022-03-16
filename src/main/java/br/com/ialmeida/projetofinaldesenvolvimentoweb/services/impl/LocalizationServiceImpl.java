package br.com.ialmeida.projetofinaldesenvolvimentoweb.services.impl;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Localization;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.repositories.LocalizationRepository;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.services.LocalizationService;
import org.springframework.stereotype.Service;

@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final LocalizationRepository localizationRepository;

    public LocalizationServiceImpl(LocalizationRepository localizationRepository) {
        this.localizationRepository = localizationRepository;
    }

    public Localization insert(Localization localization) {
        return localizationRepository.save(localization);
    }

}

package br.com.ialmeida.projetofinaldesenvolvimentoweb.services;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Localization;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.repositories.LocalizationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Testing Localization Service")
class LocalizationServiceTest {

    @Autowired
    private LocalizationRepository localizationRepository;

    private LocalizationService localizationService;

    @BeforeEach
    public void init() {
        localizationService = new LocalizationService(localizationRepository);
    }

    @Test
    @DisplayName("Verify if repository is null")
    public void localizationRepositoryIsNotNull() {
        assertNotNull(localizationRepository);
    }

    @Test
    @DisplayName("Verify if service is null")
    public void localizationServiceIsNotNull() {
        assertNotNull(localizationService);
    }

    @Test
    @DisplayName("Insert localization when successful")
    void insertLocalizationWhenSuccessful() {
        Localization localizationCreated = createLocalization();

        Localization localizationSaved = localizationService.insert(localizationCreated);

        Assertions.assertThat(localizationSaved).isNotNull();
        Assertions.assertThat(localizationSaved.getId()).isNotNull();
        Assertions.assertThat(localizationSaved.getLat()).isNotNull();
        Assertions.assertThat(localizationSaved.getLon()).isNotNull();
        Assertions.assertThat(localizationSaved.getGalaxyName()).isNotNull();
    }

    private Localization createLocalization() {
        return new Localization(null, 87.91, 90.32, "Andromeda");
    }
}
package br.com.ialmeida.projetofinaldesenvolvimentoweb.config;

import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Localization;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.Rebel;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.entities.enums.Gender;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.repositories.LocalizationRepository;
import br.com.ialmeida.projetofinaldesenvolvimentoweb.repositories.RebelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private final RebelRepository rebelRepository;
    private final LocalizationRepository localizationRepository;

    public TestConfig(RebelRepository rebelRepository, LocalizationRepository localizationRepository) {
        this.rebelRepository = rebelRepository;
        this.localizationRepository = localizationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Localization l1 = new Localization(null, 23.5, 89.76, "Via Láctea");
        Localization l2 = new Localization(null, 25.9, 19.12, "Andrômeda");
        localizationRepository.saveAll(Arrays.asList(l1, l2));

        Rebel r1 = new Rebel(null, "Iuri", 22, Gender.MALE, l2);
        Rebel r2 = new Rebel(null, "Fernanda", 34, Gender.FEMALE, l1);
        rebelRepository.saveAll(Arrays.asList(r1, r2));

    }

}

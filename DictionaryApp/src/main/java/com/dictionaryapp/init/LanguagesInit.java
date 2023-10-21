package com.dictionaryapp.init;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.enums.LanguageName;
import com.dictionaryapp.repo.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LanguagesInit implements CommandLineRunner {
    private final LanguageRepository languageRepository;

    public LanguagesInit(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (languageRepository.count() == 0){

            List<Language> languageList = new ArrayList<>();

            for (LanguageName languageName : LanguageName.values()) {
                    Language language = new Language();
                    language.setLanguageName(languageName);
                    languageList.add(language);
            }

            languageRepository.saveAll(languageList);

        }



    }
}

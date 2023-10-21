package com.dictionaryapp.service;

import com.dictionaryapp.model.DTOs.AddWordDTO;
import com.dictionaryapp.model.DTOs.HomeDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;
    private final WordRepository wordRepository;
    private final LoggedUser loggedUser;

    public WordServiceImpl(LanguageRepository languageRepository, UserRepository userRepository, WordRepository wordRepository, LoggedUser loggedUser) {
        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public void add(AddWordDTO addWordDTO) {

        String languageName = addWordDTO.getLanguage();
        List<Language> list = languageRepository.findAll();
        Language languageFromDB = new Language();

        for (Language language : list) {
            if (language.getLanguageName().name().equals(languageName)) {
                languageFromDB = language;
            }
        }

        User user = userRepository.findUserByid(loggedUser.getId());

        if (languageFromDB != null && user != null) {
            Word word = new Word();

            word.setTerm(addWordDTO.getTerm());
            word.setExample(addWordDTO.getExample());
            word.setTranslation(addWordDTO.getTranslation());
            word.setAddedBy(user);
            word.setLanguage(languageFromDB);
            word.setInputDate(addWordDTO.getInputDate());

            wordRepository.save(word);
        }


    }

    @Override
    public HomeDTO getAllWords() {

        List<Word> allWords = wordRepository.findAll();

        List<Word> germanWords = new ArrayList<>();
        List<Word> frenchWords= new ArrayList<>();
        List<Word> spanishWords= new ArrayList<>();
        List<Word> italianWords= new ArrayList<>();
        int count = 0;

        for (Word word : allWords) {
            String language = word.getLanguage().getLanguageName().name();

            switch (language){
                case "GERMAN" -> {germanWords.add(word); count++;}
                case "SPANISH" -> {spanishWords.add(word); count++;}
                case "FRENCH" -> {frenchWords.add(word); count++;}
                case "ITALIAN" -> {italianWords.add(word); count++;}
            }
        }

        HomeDTO homeDTO = new HomeDTO(germanWords,frenchWords,spanishWords,italianWords, count);


        return homeDTO;
    }

    @Override
    public void removeAll() {

        wordRepository.deleteAll();

    }

    @Override
    public void remove(Long id) {
        wordRepository.deleteById(id);
    }

}

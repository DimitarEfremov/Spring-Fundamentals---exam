package com.dictionaryapp.service;

import com.dictionaryapp.model.DTOs.AddWordDTO;
import com.dictionaryapp.model.DTOs.HomeDTO;
import com.dictionaryapp.model.entity.Word;

import java.util.List;

public interface WordService {


    void add(AddWordDTO addWordDTO);

    HomeDTO getAllWords();

    void removeAll();

    void remove(Long id);
}

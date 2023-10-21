package com.dictionaryapp.model.DTOs;

import com.dictionaryapp.model.entity.Word;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HomeDTO {


    private List<Word> germanWords;
    private List<Word> frenchWords;
    private List<Word> spanishWords;
    private List<Word> italianWords;
    private int count;

    public HomeDTO() {
    }

    public HomeDTO(List<Word> germanWords, List<Word> frenchWords, List<Word> spanishWords, List<Word> italianWords, int count) {
        this.germanWords = germanWords;
        this.frenchWords = frenchWords;
        this.spanishWords = spanishWords;
        this.italianWords = italianWords;
        this.count = count;
    }
}

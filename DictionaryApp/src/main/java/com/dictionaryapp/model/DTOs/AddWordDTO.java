package com.dictionaryapp.model.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class AddWordDTO {

    @NotEmpty
    @Size(min = 2, max = 40, message = "The term length must be between 2 and 40 characters")
    private String term;

    @NotEmpty
    @Size(min = 2, max = 80, message = "The translation length must be between 2 and 80 characters")
    private String translation;

    @NotEmpty
    @Size(min = 2, max = 200, message = "The example length must be between 2 and 200 characters")
    private String example;

    @PastOrPresent(message = "The input date must be in the past or present")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputDate;

    @NotEmpty(message = "You must select a language")
    private String language;
}

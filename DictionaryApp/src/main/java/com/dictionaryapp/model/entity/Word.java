package com.dictionaryapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "words")
@Getter
@Setter
public class Word extends BaseEntity {

    @Column(nullable = false)
    @Size (min = 2, max = 40)
    private String term;

    @Column(nullable = false)
    @Size (min = 2, max = 80)
    private String translation;

    @Size (min = 2, max = 200)
    private String example;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputDate;

    @ManyToOne
    private Language language;

    @ManyToOne
    private User addedBy;






}

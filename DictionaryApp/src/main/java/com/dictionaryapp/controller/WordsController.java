package com.dictionaryapp.controller;

import com.dictionaryapp.model.DTOs.AddWordDTO;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WordsController {

    private final WordService wordService;
    private final UserService userService;
    private final LoggedUser loggedUser;

    public WordsController(WordService wordService, UserService userService, LoggedUser loggedUser) {
        this.wordService = wordService;
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/words/add")
    public ModelAndView addWord(
            @ModelAttribute("addWordDTO") AddWordDTO addWordDTO) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("word-add");
    }

    @PostMapping("/words/add")
    public ModelAndView addWord(
//          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          @ModelAttribute("addWordDTO") @Valid AddWordDTO addWordDTO,
           BindingResult bindingResult) {

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("word-add");
        }

        wordService.add(addWordDTO);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/home/remove-all")
    public ModelAndView remove() {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        wordService.removeAll();

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/home/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        wordService.remove(id);

        return new ModelAndView("redirect:/home");
    }


}

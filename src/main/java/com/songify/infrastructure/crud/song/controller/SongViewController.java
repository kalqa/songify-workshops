package com.songify.infrastructure.crud.song.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SongViewController {

    private Map<Integer, String> database = new HashMap<>();

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/view/songs")
    public String songs(Model model){
        database.put(1, "shawnmendes song1");
        database.put(2, "ariana grande song2");
        database.put(3, "ariana grande song21123123");
        database.put(4, "ariana grande song12312314345cbvbcvb");
        database.put(5, "ariana grande song12312314345cbvbcvb");
        database.put(6, "ariana grande song12312314345cbvbcvb");
        model.addAttribute("songMap", database);
        return "songs";
    }
}

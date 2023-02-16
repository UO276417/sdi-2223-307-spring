package com.uniovi.sdi2223307spring.controllers;

import com.uniovi.sdi2223307spring.entities.*;
import com.uniovi.sdi2223307spring.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
public class MarksController {

    @Autowired
    private MarksService marksService;

    @RequestMapping("/mark/list")
    public String getList() {

        return marksService.getMarks().toString();
    }
    @RequestMapping(value = "/mark/add", method = RequestMethod.POST)
    public String setMark(@ModelAttribute Mark mark) {
        marksService.addMark(mark);
        return "OK";
    }
    @RequestMapping("/mark/details/{id}")
    public String getDetail(@PathVariable Long id) {

        return marksService.getMark(id).toString();
    }

    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        marksService.deleteMark(id);
        return "OK";
    }
}

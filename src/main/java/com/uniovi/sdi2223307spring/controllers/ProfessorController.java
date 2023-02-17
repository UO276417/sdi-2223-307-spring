package com.uniovi.sdi2223307spring.controllers;

import com.uniovi.sdi2223307spring.entities.*;
import com.uniovi.sdi2223307spring.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class ProfessorController {

    @Autowired
    private ProfessorsService professorsService;
    @RequestMapping("/professor/list")
    public String getList(Model model) {
        model.addAttribute("profList", professorsService.getProfessors());
        return "professor/list";
    }
    @RequestMapping(value = "/professor/add", method = RequestMethod.POST)
    public String setProfessor(@ModelAttribute Professor prof) {
        professorsService.addProfessor(prof);
        return "redirect:/professor/list";
    }
    @RequestMapping("/professor/details/{dni}")
    public String getDetail(@PathVariable Long dni) {
        return professorsService.getProfessor(dni).toString();
    }

    @RequestMapping("/professor/delete/{dni}")
    public String deleteProfessor(@PathVariable Long dni) {
        professorsService.deleteprofessor(dni);
        return "redirect:/professor/list";
    }
}

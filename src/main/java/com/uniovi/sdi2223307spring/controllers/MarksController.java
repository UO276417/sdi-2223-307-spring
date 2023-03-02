package com.uniovi.sdi2223307spring.controllers;

import com.uniovi.sdi2223307spring.entities.*;
import com.uniovi.sdi2223307spring.services.*;
import com.uniovi.sdi2223307spring.validators.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;
import com.uniovi.sdi2223307spring.entities.User;


import javax.servlet.http.*;
import java.security.*;
import java.util.*;

@Controller
public class MarksController {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private MarkFormValidator marksValidator;
    @Autowired
    private MarksService marksService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("/mark/list")
    public String getList(Model model, Principal principal ,@RequestParam(value="", required = false) String searchText){
        String dni = principal.getName(); // DNI es el name de la autenticación
        User user = usersService.getUserByDni(dni);
        if(searchText != null && !searchText.isEmpty()){
            model.addAttribute("markList", marksService.searchMarksByDescriptionAndNameForUser(searchText, user) );
        }else{
            model.addAttribute("markList", marksService.getMarksForUser(user) );
        }

        return "mark/list";
    }

    @RequestMapping(value = "/mark/{id}/resend", method = RequestMethod.GET)
    public String setResendTrue(@PathVariable Long id) {
        marksService.setMarkResend(true, id);
        return "redirect:/mark/list";
    }
    @RequestMapping(value = "/mark/{id}/noresend", method = RequestMethod.GET)
    public String setResendFalse(@PathVariable Long id) {
        marksService.setMarkResend(false, id);
        return "redirect:/mark/list";
    }

    @RequestMapping(value = "/mark/add", method = RequestMethod.POST)
    public String setMark(@Validated Mark mark, BindingResult result, Model model) {
        marksValidator.validate(mark, result);
        model.addAttribute("usersList", usersService.getUsers());
        model.addAttribute("mark",mark);

        if(result.hasErrors()){
            return "/mark/add";
        }

        marksService.addMark(mark);
        return "redirect:/mark/list";

    }
    @RequestMapping("/mark/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        return "mark/details";
    }

    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        marksService.deleteMark(id);
        return "redirect:/mark/list";
    }

    @RequestMapping(value = "/mark/add")
    public String getMark(Model model) {
        model.addAttribute("usersList", usersService.getUsers());
        return "mark/add";
    }

    @RequestMapping(value = "/mark/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        model.addAttribute("usersList", usersService.getUsers());
        return "mark/edit";
    }

    @RequestMapping(value="/mark/edit/{id}", method=RequestMethod.POST)
    public String setEdit(@Validated Mark mark, @PathVariable Long id, BindingResult result){
        marksValidator.validate(mark, result);
        if(result.hasErrors()){
            return "/mark/edit/" + id;
        }

        Mark originalMark = marksService.getMark(id);
        // modificar solo score y description
        originalMark.setScore(mark.getScore());
        originalMark.setDescription(mark.getDescription());
        marksService.addMark(originalMark);
        return "redirect:/mark/details/"+id;
    }

    @RequestMapping("/mark/list/update")
    public String updateList(Model model, Principal principal) {
        String dni = principal.getName(); // DNI es el name de la autenticación
        User user = usersService.getUserByDni(dni);
        model.addAttribute("markList", marksService.getMarksForUser(user));
        return "mark/list :: tableMarks";
    }

}

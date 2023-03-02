package com.uniovi.sdi2223307spring.services;

import com.uniovi.sdi2223307spring.Repositories.*;
import com.uniovi.sdi2223307spring.entities.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;

import javax.annotation.*;
import javax.servlet.http.*;
import java.util.*;

@Service
public class MarksService {
    @Autowired
    private MarksRepository marksRepository;

    private final HttpSession httpSession;
    @Autowired
    public MarksService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public List<Mark> getMarks() {
        List<Mark> marks = new ArrayList<Mark>();
        marksRepository.findAll().forEach(marks::add);
        return marks;
    }
    public Mark getMark(Long id){
        return marksRepository.findById(id).get();
    }

    public void setMarkResend(boolean revised, Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String dni = auth.getName();
        Mark mark = marksRepository.findById(id).get();
        if(mark.getUser().getDni().equals(dni) ) {
            marksRepository.updateResend(revised, id);
        }
    }
    public void addMark(Mark mark) {
        // Si en Id es null le asignamos el ultimo + 1 de la lista
        marksRepository.save(mark);
    }
    public void deleteMark(Long id) {
        marksRepository.deleteById(id);
    }

    public List<Mark> getMarksForUser(User user) {
        List<Mark> marks = new ArrayList<>();
        if (user.getRole().equals("ROLE_STUDENT")) {
            marks = marksRepository.findAllByUser(user);}
        if (user.getRole().equals("ROLE_PROFESSOR")) {
            marks = getMarks(); }
        return marks;
    }

}

package com.uniovi.sdi2223307spring.validators;

import com.uniovi.sdi2223307spring.entities.*;
import com.uniovi.sdi2223307spring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;

@Component
public class ProfessorValidator implements Validator{
    @Autowired
    private ProfessorsService professorService;
    @Override
    public boolean supports(Class<?> aClass) {
        return Professor.class.equals(aClass);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Professor prof = (Professor) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "Error.empty");
        char lastValue = prof.getDni().toString().charAt(prof.getDni().toString().length() - 1);
        if (prof.getDni().toString().length() < 9 || !Character.isLetter(lastValue) ) {
            errors.rejectValue("dni", "Error.professor.dni.length");}
        if (professorService.getProfessor(prof.getDni()) != null) {
            errors.rejectValue("id", "Error.professor.dni.duplicate");}
    }
}


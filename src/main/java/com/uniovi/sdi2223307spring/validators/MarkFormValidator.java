package com.uniovi.sdi2223307spring.validators;

import com.uniovi.sdi2223307spring.entities.*;
import com.uniovi.sdi2223307spring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;

@Component
public class MarkFormValidator implements Validator{
    @Autowired
    private MarksService marksService;
    @Override
    public boolean supports(Class<?> aClass) {
        return Mark.class.equals(aClass);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Mark mark = (Mark) target;
        if (mark.getScore() < 0 || mark.getScore() > 10) {
            errors.rejectValue("score", "Error.mark.score.range");}
        //if (mark.getDescription().length() <20 ) {
         //   errors.rejectValue("description", "Error.mark.description.length");
        //}
     }
}

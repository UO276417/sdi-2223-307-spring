package com.uniovi.sdi2223307spring.services;

import com.uniovi.sdi2223307spring.entities.*;
import org.springframework.stereotype.*;

import javax.annotation.*;
import java.util.*;

@Service
public class ProfessorsService {

    private List<Professor> profList = new LinkedList<>();
    @PostConstruct
    public void init() {
        profList.add(new Professor(1L, "Pablo","Panda",  "Psicologia"));
        profList.add(new Professor(2L, "Berto","Balbuena",  "Tecnologia"));
    }
    public List<Professor> getProfessors() {
        return profList;
    }
    public Professor getProfessor(Long dni) {
        return profList.stream()
                .filter(professor -> professor.getDni().equals(dni)).findFirst().get();
    }
    public void addProfessor(Professor professor) {
        // Si en Id es null le asignamos el ultimo + 1 de la lista
        if (professor.getDni() == null) {
            professor.setDni(profList.get(profList.size() - 1).getDni() + 1);
        }
        profList.add(professor);
    }
    public void deleteprofessor(Long dni) {
        profList.removeIf(professor -> professor.getDni().equals(dni));
    }

}

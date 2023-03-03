package com.uniovi.sdi2223307spring.services;

import com.uniovi.sdi2223307spring.Repositories.*;
import com.uniovi.sdi2223307spring.entities.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.annotation.*;
import java.util.*;

@Service
public class ProfessorsService {
    @Autowired
    private ProfessorsRepository professorsRepository;

    public List<Professor> getProfessors() {

        List<Professor> profs = new ArrayList<Professor>();
        professorsRepository.findAll().forEach(profs::add);
        return profs;
    }
    public Professor getProfessor(Long dni) {
        return professorsRepository.findById(dni).get();
    }
    public void addProfessor(Professor professor) {
        professorsRepository.save(professor);
    }
    public void deleteprofessor(Long dni) {
        professorsRepository.deleteById(dni);
    }
}

package com.uniovi.sdi2223307spring.Repositories;

import com.uniovi.sdi2223307spring.entities.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.CrudRepository;

public interface MarksRepository extends CrudRepository<Mark, Long> {
}
package com.uniovi.sdi2223307spring.Repositories;

import com.uniovi.sdi2223307spring.entities.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
public interface MarksRepository extends CrudRepository<Mark, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Mark SET resend = ?1 WHERE id = ?2")
    void updateResend(Boolean resend, Long id);
}

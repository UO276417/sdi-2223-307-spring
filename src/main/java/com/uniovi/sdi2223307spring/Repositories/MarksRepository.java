package com.uniovi.sdi2223307spring.Repositories;

import com.uniovi.sdi2223307spring.entities.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public interface MarksRepository extends CrudRepository<Mark, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Mark SET resend = ?1 WHERE id = ?2")
    void updateResend(Boolean resend, Long id);

    @Query("SELECT r FROM Mark r WHERE r.user = ?1 ORDER BY r.id ASC")
    List<Mark> findAllByUser(User user);

}

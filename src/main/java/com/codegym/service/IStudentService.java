package com.codegym.service;

import com.codegym.IGeneralService;
import com.codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface IStudentService extends IGeneralService<Student> {
    Page<Student> findAll(Pageable pageable);

    Page<Student> findAllByNameContaining(String firstname, Pageable pageable);
}

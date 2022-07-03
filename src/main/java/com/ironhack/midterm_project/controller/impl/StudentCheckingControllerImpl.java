package com.ironhack.midterm_project.controller.impl;

import com.ironhack.midterm_project.controller.interfaces.StudentCheckingController;
import com.ironhack.midterm_project.repository.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentCheckingControllerImpl implements StudentCheckingController {

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
}

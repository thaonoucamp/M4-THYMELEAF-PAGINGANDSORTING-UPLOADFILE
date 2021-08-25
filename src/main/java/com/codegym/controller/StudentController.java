package com.codegym.controller;

import com.codegym.model.ClassRoom;
import com.codegym.model.Student;
import com.codegym.service.IClassRoomService;
import com.codegym.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private IClassRoomService classRoomService;

//    @GetMapping("/search-by-name")
//    public ModelAndView listClassRoom(@RequestParam("name") Optional<String> name, Pageable pageable) {
//        Page<Student> students;
//        if (name.isPresent()) {
//            students = studentService.findAllByNameContaining(name.get(), pageable);
//        } else {
//            students = studentService.findAll(pageable);
//        }
//        ModelAndView modelAndView = new ModelAndView("/home");
//        modelAndView.addObject("students", students);
//        return modelAndView;
//    }

    @GetMapping("/home")
    public ModelAndView showAllStudent(@RequestParam(defaultValue = "1") int students) {
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("students", studentService.findAll(PageRequest.of(students, 2, Sort.by("name"))));
        return modelAndView;
    }

    @ModelAttribute("classRooms")
    public List<ClassRoom> getClassRoom() {
        return (List<ClassRoom>) classRoomService.findAll();
    }

    @GetMapping("/create-student")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create-student")
    public ModelAndView save(@RequestParam MultipartFile multipartFile,
                             @Valid @ModelAttribute("student") Student student,
                             BindingResult bindingResult) throws IOException {
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("/create");
        }
        String fileName = multipartFile.getOriginalFilename();
        FileCopyUtils.copy(multipartFile.getBytes(), new File("/Users/thaodangxuan/untitled/src/main/webapp/WEB-INF/image/" + fileName));
        student.setAvatar("/miniPath/" + fileName);
        studentService.save(student);
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("student", student);
        return modelAndView;
    }
//
//    @GetMapping("/edit-student/{id}")
//    public ModelAndView showEditForm(@PathVariable Long id) {
//        Optional<Student> student = studentService.findById(id);
//        if (student.isPresent()) {
//            ModelAndView modelAndView = new ModelAndView("/edit");
//            modelAndView.addObject("student", student.get());
//            return modelAndView;
//        } else {
//            ModelAndView modelAndView = new ModelAndView("/error");
//            return modelAndView;
//        }
//    }
//
//    @PostMapping("/edit-student")
//    public ModelAndView update(@ModelAttribute("student") Student student) {
//        studentService.save(student);
//        ModelAndView modelAndView = new ModelAndView("/edit");
//        modelAndView.addObject("student", student);
//        return new ModelAndView("redirect:/home");
//    }
//
//    @GetMapping("/delete-student/{id}")
//    public ModelAndView showDeleteForm(@PathVariable Long id) {
//        Optional<Student> student = studentService.findById(id);
//        if (student.isPresent()) {
//            ModelAndView modelAndView = new ModelAndView("/delete");
//            modelAndView.addObject("student", student.get());
//            return modelAndView;
//        } else {
//            ModelAndView modelAndView = new ModelAndView("/error");
//            return modelAndView;
//        }
//    }
//
//    @PostMapping("/delete-student")
//    public String delete(@ModelAttribute("student") Student student) {
//        studentService.remove(student.getId());
//        return "redirect:/home";
//    }

}

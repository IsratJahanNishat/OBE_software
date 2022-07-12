package com.dev.FinalProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CoursePageController {

	@RequestMapping("/gotoCourse")
    public String index45() {
        return "CoursePage";
    }
}

package com.lisi.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/workbench/main/toIndex.do")
    public String toIndex() {
        return "workbench/main/index";
    }
}

package com.whaleread.showcase.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Dolphin
 */
@Controller
public class TableController {
    @GetMapping("/table")
    public String table(Model model) {
        return "table";
    }
}

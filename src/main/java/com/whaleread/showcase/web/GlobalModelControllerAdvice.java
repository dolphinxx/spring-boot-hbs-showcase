package com.whaleread.showcase.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author Dolphin
 */
@ControllerAdvice
public class GlobalModelControllerAdvice {

    @ModelAttribute("static_base")
    public String staticBase() {
        return "";
    }
}

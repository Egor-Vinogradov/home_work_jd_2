package by.it_academy.jd2.crm.controllers.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StartController {

    @RequestMapping
    public String indexPage() {
        return "index";
    }
}

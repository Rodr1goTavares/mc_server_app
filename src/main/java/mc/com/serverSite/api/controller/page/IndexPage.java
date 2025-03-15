package mc.com.serverSite.api.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPage {

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

}

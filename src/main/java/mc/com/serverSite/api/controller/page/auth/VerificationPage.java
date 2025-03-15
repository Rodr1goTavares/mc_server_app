package mc.com.serverSite.api.controller.page.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/page")
public class VerificationPage {

    @GetMapping("/verification")
    public String verificationPage(Model model) {
        return "verification";
    }

}

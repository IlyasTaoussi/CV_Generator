package s8project.cv.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class MainController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/index.html").setViewName("HomePage");
    }

    @GetMapping("/")
    public String redirectGet(){
        return "redirect:/index.html";
    }

    @PostMapping("/")
    public String redirectPost(){
        return "redirect:/index.html";
    }
}

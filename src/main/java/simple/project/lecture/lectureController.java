package simple.project.lecture;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("class")
public class lectureController {
    @RequestMapping("{class_id}")
    public String someMethod(
            Model model,
            @PathVariable("class_id") String classId
    ){
        model.addAttribute("classId", classId);
        return "class/mainClass";
    }
}

package simple.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Tiger {
    @RequestMapping("/t1")
    public String func01(){
        System.out.println("func01 call");
        return "TigerView";// View로 가려고 시도 합니다.
        // return "---" 하면 Views 폴더 내의 --- 를 찾는다.
        // 일반적으로 클래스이름 뒤에 View를 붙여서 작성한다.
    }

}

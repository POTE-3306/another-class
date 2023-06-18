package simple.project.lecture;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import simple.project.attendance.AttendanceService;
import simple.project.config.SessionInterceptor;
import simple.project.courseplan.CoursePlan;
import simple.project.courseplan.CoursePlanService;
import simple.project.post.Post;
import simple.project.registration.Registration;
import simple.project.registration.RegistrationService;
import simple.project.user.JWToken;
import simple.project.user.User;
import simple.project.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import simple.project.course.Course;
import simple.project.course.CourseService;
import simple.project.post.PostService;

@Controller
@RequestMapping("lecture")
public class lectureController {
    private final JWToken jwToken;
    private final UserService userService;
    private final CoursePlanService coursePlanService;
    private final CourseService courseService;
    private final PostService postService;
    private final RegistrationService registrationService;
    private final AttendanceService attendanceService;

    @Autowired
    public lectureController(JWToken jwToken, UserService userService, CoursePlanService coursePlanService, CourseService courseService, PostService postService, RegistrationService registrationService, AttendanceService attendanceService) {
        this.jwToken = jwToken;
        this.userService = userService;
        this.coursePlanService = coursePlanService;
        this.courseService = courseService;
        this.postService = postService;
        this.registrationService = registrationService;
        this.attendanceService = attendanceService;
    }

    private User getUserById(List<User> users, int userId) {
        return users.stream().filter(user -> user.getId() == userId).findFirst().orElse(null);
    }

    @RequestMapping("{class_id}")
    public String mainClass(
            HttpServletRequest request,
            Model model,
            @PathVariable("class_id") String classId
    ) {
        User user = (User) request.getAttribute("user");
        try {
            Course course = courseService.getCourseById(Integer.parseInt(classId));
            model.addAttribute("user", user);
            model.addAttribute("course", course);
            model.addAttribute("classId", classId);
        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
        return "class/mainClass";
    }

    @RequestMapping("{class_id}/community")
    public String communityPage(
            HttpSession session,
            Model model,
            @PathVariable("class_id") String classId
    ) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            return "index";
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);

            if (user == null) {
                return "index";
            }
            model.addAttribute("user", user);

        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
        return String.format("redirect:/lecture/%s/community/notice", classId);
    }

    @RequestMapping("{class_id}/community/notice")
    public String communityNoticePage(
            HttpSession session,
            Model model,
            @PathVariable("class_id") String classId
    ) {
        String token = (String) session.getAttribute("token");
        Integer pageType = 1;
        if (token == null) {
            return "index";
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);
            List<Post> postList = postService.getByClassIdAndBoardType(Integer.parseInt(classId), pageType);

            if (user == null) {
                return "index";
            }
            model.addAttribute("user", user);
            model.addAttribute("postList", postList);
            model.addAttribute("classId", classId);
            model.addAttribute("pageType", pageType);

        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
        return "class/community";
    }

    @RequestMapping("{class_id}/community/task")
    public String communityTaskPage(
            HttpSession session,
            Model model,
            @PathVariable("class_id") String classId
    ) {
        String token = (String) session.getAttribute("token");
        Integer pageType = 2;
        if (token == null) {
            return "index";
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);
            List<Post> postList = postService.getByClassIdAndBoardType(Integer.parseInt(classId), pageType);
            if (user == null) {
                return "index";
            }
            model.addAttribute("user", user);
            model.addAttribute("postList", postList);
            model.addAttribute("classId", classId);
            model.addAttribute("pageType", pageType);

        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
        return "class/community";
    }

    @RequestMapping("{class_id}/community/talk")
    public String communityTalkPage(
            HttpSession session,
            Model model,
            @PathVariable("class_id") String classId
    ) {
        String token = (String) session.getAttribute("token");
        Integer pageType = 4;
        if (token == null) {
            return "index";
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);
            List<Post> postList = postService.getByClassIdAndBoardType(Integer.parseInt(classId), pageType);

            if (user == null) {
                return "index";
            }
            model.addAttribute("user", user);
            model.addAttribute("postList", postList);
            model.addAttribute("classId", classId);
            model.addAttribute("pageType", pageType);

        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
        return "class/community";
    }

    @RequestMapping("{class_id}/community/material")
    public String communityProgressPage(
            HttpSession session,
            Model model,
            @PathVariable("class_id") String classId
    ) {
        String token = (String) session.getAttribute("token");
        Integer pageType = 3;
        if (token == null) {
            return "index";
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);
            List<Post> postList = postService.getByClassIdAndBoardType(Integer.parseInt(classId), pageType);

            if (user == null) {
                return "index";
            }
            model.addAttribute("user", user);
            model.addAttribute("postList", postList);
            model.addAttribute("classId", classId);
            model.addAttribute("pageType", pageType);

        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
        return "class/community";
    }

    @RequestMapping("{class_id}/manage")
    public String attendPage(
            HttpSession session,
            Model model,
            @PathVariable("class_id") String classId
    ) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            return "index";
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);
            Course course = courseService.getCourseById(Integer.parseInt(classId));
            List<User> users = userService.findAllUser();

            if (user == null) {
                return "index";
            }
            model.addAttribute("user", user);
            model.addAttribute("course", course);
            List<Registration> reglist = registrationService.findByCourseId(course.getId());
            List<RegisterWaiting> waitingList = new ArrayList<>();
            for (Registration registration : reglist) {
                User student = getUserById(users, registration.getUserId());
                RegisterWaiting rw = new RegisterWaiting();
                if (student != null) {
                    rw.setUserName(student.getName());
                    rw.setUserId(student.getId());
                }
                rw.setRegId(registration.getId());

                System.out.println(rw);
                waitingList.add(rw);
            }
            System.out.println(waitingList);
            model.addAttribute("waitingList", waitingList);

            List<Registration> attendList = registrationService.findAttendListByCourseId(course.getId());
            List<AtendUserDto> atendUserDtos = new ArrayList<>();
            System.out.println("attendList size : " + attendList.size());
            for (Registration registration : attendList) {
                User student = getUserById(users, registration.getUserId());
                AtendUserDto audto = new AtendUserDto();
                if (student != null) {
                    audto.setUserId(student.getId());
                    audto.setName(student.getName());
                }
                audto.setCourseId(course.getId());
                audto.setId(registration.getId());
                System.out.println(audto);
                atendUserDtos.add(audto);
            }
            model.addAttribute("attendUserDtos", atendUserDtos);
            HashMap<Integer, String> attendUserToday = attendanceService.attendUserToday(course.getId());
            model.addAttribute("todayAttend", attendUserToday);
        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
        return "class/manage";
    }

//    @RequestMapping("{class_id}/attend")
//    public String managePage(
//            HttpSession session,
//            Model model,
//            @PathVariable("class_id") String classId
//    ){
//        String token = (String) session.getAttribute("token");
//        if (token == null) {
//            return "index";
//        }
//        try {
//            Claims claims = jwToken.getClaims(token);
//            User user = userService.getUserByToken(claims);
//            Course course = courseService.getCourseById(Integer.parseInt(classId));
//
//            if (user == null) {
//                return "index";
//            }
//            model.addAttribute("user", user);
//            model.addAttribute("course", course);
//
//        } catch (Exception e){
//            e.printStackTrace();
//            return "index";
//        }
//        return "class/attend";
//    }

    @RequestMapping("{class_id}/accept")
    public String acceptReg(
            @RequestParam("regId") int regId,
            @PathVariable("class_id") String classId,
            Model model,
            HttpSession session
    ) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            return "index";
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);
            Course course = courseService.getCourseById(Integer.parseInt(classId));
            System.out.println("accept hi : " + regId);
            registrationService.updateReg(regId);
            if (user == null) {
                return "index";
            }
            model.addAttribute("user", user);
            model.addAttribute("course", course);

        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }

        return String.format("redirect:/lecture/%s/manage", classId);
    }

    @RequestMapping("{class_id}/reject")
    public String rejcetReg(
            @RequestParam("regId") int regId,
            @PathVariable("class_id") String classId
    ) {
        registrationService.deleteReg(regId);
        return String.format("redirect:/lecture/%s/manage", classId);
    }

    @RequestMapping("{class_id}/attend")
    public String managePage(
            HttpSession session,
            Model model,
            @PathVariable("class_id") String classId
    ) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            return "index";
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);
            Course course = courseService.getCourseById(Integer.parseInt(classId));

            if (user == null) {
                return "index";
            }
            model.addAttribute("user", user);
            model.addAttribute("course", course);

        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
        return "class/manage";
    }

    @RequestMapping("{class_id}/community/create-post")
    public String createPostPage(@RequestParam("pageType") Integer pageType,
                                 @PathVariable("class_id") String classId,
                                 Model model) {
        System.out.println("page type :  " + pageType);
        System.out.println("class id : " + classId);
        model.addAttribute("classId", classId);
        model.addAttribute("pageType", pageType);
        return "class/makePost";
    }

    @RequestMapping("{class_id}/community/insert-post")
    public String insertPage(@PathVariable("class_id") String classId,
                             @RequestParam("pageType") Integer pageType,
                             @RequestParam("title") String title,
                             @RequestParam("content") String content,
                             HttpSession session,
                             Model model) {
        HashMap<Integer, String> pageTypeMapper = new HashMap<>();

        String token = (String) session.getAttribute("token");
        if (token == null) {
            return "index";
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);
            Course course = courseService.getCourseById(Integer.parseInt(classId));

            if (user == null) {
                return "index";
            }
            model.addAttribute("user", user);
            model.addAttribute("course", course);

            System.out.println("call insert");
            System.out.println("pageType : " + pageType);
            pageTypeMapper.put(1, "notice");
            pageTypeMapper.put(2, "task");
            pageTypeMapper.put(3, "talk");
            pageTypeMapper.put(4, "material");

            postService.insertPost(Integer.parseInt(classId), user.getId(), title, content, pageType);


        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
        return String.format("redirect:/lecture/%s/community/%s", classId, pageTypeMapper.get(pageType));
    }
}

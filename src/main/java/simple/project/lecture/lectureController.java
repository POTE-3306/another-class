package simple.project.lecture;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import simple.project.attendance.AttendanceService;
import simple.project.courseplan.CoursePlanService;
import simple.project.post.Post;
import simple.project.registration.Registration;
import simple.project.registration.RegistrationService;
import simple.project.user.JWToken;
import simple.project.user.User;
import simple.project.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
            HttpServletRequest request,
            Model model,
            @PathVariable("class_id") String classId
    ) {
        User user = (User) request.getAttribute("user");
        model.addAttribute("user", user);
        return String.format("redirect:/lecture/%s/community/notice", classId);
    }

    @RequestMapping("{class_id}/community/notice")
    public String communityNoticePage(
            HttpServletRequest request,
            Model model,
            @PathVariable("class_id") String classId
    ) {
        User user = (User) request.getAttribute("user");
        Integer pageType = 1;
        try {
            List<Post> postList = postService.getByClassIdAndBoardType(Integer.parseInt(classId), pageType);
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
            HttpServletRequest request,
            Model model,
            @PathVariable("class_id") String classId
    ) {
        User user = (User) request.getAttribute("user");
        Integer pageType = 2;
        try {
            List<Post> postList = postService.getByClassIdAndBoardType(Integer.parseInt(classId), pageType);
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
            HttpServletRequest request,
            Model model,
            @PathVariable("class_id") String classId
    ) {
        User user = (User) request.getAttribute("user");
        Integer pageType = 4;
        try {
            List<Post> postList = postService.getByClassIdAndBoardType(Integer.parseInt(classId), pageType);
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
            HttpServletRequest request,
            Model model,
            @PathVariable("class_id") String classId
    ) {
        User user = (User) request.getAttribute("user");
        Integer pageType = 3;
        try {
            List<Post> postList = postService.getByClassIdAndBoardType(Integer.parseInt(classId), pageType);
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
    public String mangePage(
            Model model,
            @PathVariable("class_id") int classId,
            HttpServletRequest request
    ) {
        User user = (User) request.getAttribute("user");
        Course course = courseService.getCourseById(classId);
        model.addAttribute("user", user);
        model.addAttribute("course", course);
        return "class/manage";
    }

    @RequestMapping("{class_id}/modify")
    public String classModify(
            Model model,
            @PathVariable("class_id") int classId
    ) {
        Course course = courseService.getCourseById(classId);
        model.addAttribute("course", course);
        return "class/modifyClass";
    }

    @RequestMapping("{class_id}/registerManage")
    public String registerManagePage(
            HttpServletRequest request,
            Model model,
            @PathVariable("class_id") int classId
    ) {
        User user = (User) request.getAttribute("user");
        try {
            List<User> users = userService.findAllUser();
            model.addAttribute("user", user);
            List<Registration> reglist = registrationService.findByCourseId(classId);
            List<RegisterWaiting> waitingList = new ArrayList<>();
            for (Registration registration : reglist) {
                User student = getUserById(users, registration.getUserId());
                RegisterWaiting rw = new RegisterWaiting();
                if (student != null) {
                    rw.setUserName(student.getName());
                    rw.setUserId(student.getId());
                }
                rw.setRegId(registration.getId());
                waitingList.add(rw);
            }
            model.addAttribute("waitingList", waitingList);
            model.addAttribute("classId", classId);
        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
        return "class/registerManage";
    }

    @RequestMapping("{class_id}/attendManage")
    public String attendManagePage(
            HttpServletRequest request,
            Model model,
            @PathVariable("class_id") int classId
    ) {
        User user = (User) request.getAttribute("user");
        try {
            List<User> users = userService.findAllUser();
            model.addAttribute("user", user);
            List<Registration> attendList = registrationService.findAttendListByCourseId(classId);
            List<AtendUserDto> atendUserDtos = new ArrayList<>();
            for (Registration registration : attendList) {
                User student = getUserById(users, registration.getUserId());
                if(student.isAdmin())
                    continue;
                AtendUserDto audto = new AtendUserDto();
                if (student != null) {
                    audto.setUserId(student.getId());
                    audto.setName(student.getName());
                }
                audto.setCourseId(classId);
                audto.setId(registration.getId());
                atendUserDtos.add(audto);
            }
            model.addAttribute("attendUserDtos", atendUserDtos);
            HashMap<Integer, String> attendUserToday = attendanceService.attendUserToday(classId);
            model.addAttribute("todayAttend", attendUserToday);
            model.addAttribute("classId", classId);
            HashMap<Integer, Integer> atendRateMap = new HashMap<>();
            for (User user1 : users) {
                Integer rate = attendanceService.atendRate(classId, user1.getId());
                atendRateMap.put(user1.getId(), rate);
            }
            model.addAttribute("rate", atendRateMap);
        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
        return "class/attendManage";
    }

    @RequestMapping("{class_id}/accept")
    public String acceptReg(
            @RequestParam("regId") int regId,
            @PathVariable("class_id") String classId,
            Model model,
            HttpServletRequest request
    ) {
        User user = (User) request.getAttribute("user");
        try {
            Course course = courseService.getCourseById(Integer.parseInt(classId));
            registrationService.updateReg(regId);
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
            HttpServletRequest request,
            Model model,
            @PathVariable("class_id") String classId
    ) {
        User user = (User) request.getAttribute("user");
        try {
            Course course = courseService.getCourseById(Integer.parseInt(classId));
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
        model.addAttribute("classId", classId);
        model.addAttribute("pageType", pageType);
        return "class/makePost";
    }

    @RequestMapping("{class_id}/community/insert-post")
    public String insertPage(@PathVariable("class_id") String classId,
                             @RequestParam("pageType") Integer pageType,
                             @RequestParam("title") String title,
                             @RequestParam("content") String content,
                             HttpServletRequest request,
                             Model model) {
        HashMap<Integer, String> pageTypeMapper = new HashMap<>();
        User user = (User) request.getAttribute("user");

        try {
            Course course = courseService.getCourseById(Integer.parseInt(classId));
            model.addAttribute("user", user);
            model.addAttribute("course", course);

            pageTypeMapper.put(1, "notice");
            pageTypeMapper.put(2, "task");
            pageTypeMapper.put(4, "talk");
            pageTypeMapper.put(3, "material");
            postService.insertPost(Integer.parseInt(classId), user.getId(), title, content, pageType);
        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
        return String.format("redirect:/lecture/%s/community/%s", classId, pageTypeMapper.get(pageType));
    }
}

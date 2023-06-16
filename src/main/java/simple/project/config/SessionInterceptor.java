package simple.project.config;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import simple.project.user.JWToken;
import simple.project.user.User;
import simple.project.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInterceptor implements HandlerInterceptor {
    private final UserService userService;
    private final JWToken jwToken;

    @Autowired
    public SessionInterceptor(UserService userService, JWToken jwToken) {
        this.userService = userService;
        this.jwToken = jwToken;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");

        if (token == null || token.isEmpty()) {
            response.sendRedirect("/another-class");
            return false;
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);
            if (user == null) {
                response.sendRedirect("/another-class");
                return false;
            }
            request.setAttribute("user", user);
        } catch (Exception e) {
            response.sendRedirect("/another-class");
            return false;
        }
        return true;
    }
}

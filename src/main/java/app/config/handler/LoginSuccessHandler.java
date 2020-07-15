package app.config.handler;

import app.entity.Role;
import app.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {

        User user = (User) authentication.getPrincipal();

        boolean adminFlag = false;
        boolean userFlag = false;

        for (Role i : user.getRole()) {
            if (!adminFlag) {
                adminFlag = (i.getRole().equals("ROLE_ADMIN"));
            }
            if (!userFlag) {
                userFlag = (i.getRole().equals("ROLE_USER"));
            }
        }

        if (adminFlag) {
            httpServletResponse.sendRedirect("/admin/");
        } else if (userFlag) {
            httpServletResponse.sendRedirect("/user/");
        } else {
            httpServletResponse.sendRedirect("/error");
        }
    }
}

package filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/*"})
public class CustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = request.getServletPath();
        String contextPath = request.getContextPath();
        String role = "";
        String email = "";

        HttpSession session = request.getSession();

        if (session.getAttribute("email")!=null){
            email = (String) session.getAttribute("email");
            role = (String) session.getAttribute("role");
        }

        System.out.println("role: " + role);
        System.out.println("email"+email);

        //Trang login và 403 khi call sẽ được thông qua, tránh trường hợp lặp vô tận.
        if (servletPath.endsWith("/login")||servletPath.endsWith("/403")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            // Kiểm tra quyền truy cập

            // role = "" có nghĩa là user chưa đăng nhập, chuyển người dùng về trang login.
            if(role.equals("")){
                response.sendRedirect(contextPath+"/login");
            }else {
                switch (role){
                    case "ROLE_ADMIN":{
                        filterChain.doFilter(servletRequest,servletResponse);
                        break;
                    }
                    case "ROLE_LEADER":{

                        break;
                    }
                    case "ROLE_USER":{
                        // user chỉ có thể cập nhật tiến độ dự án mà mình đã và đang tham gia( tại trang profile)
                        if (servletPath.endsWith("/index.jsp")||servletPath.endsWith("/profile")||servletPath.endsWith("/edit")||servletPath.endsWith("/logout")){
                            filterChain.doFilter(servletRequest,servletResponse);
                        }else {
                            response.sendRedirect(contextPath+"/403");
                        }
                        break;
                    }

                }

            }
        }


    }
}

package cn.smartrick.soul.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域请求拦截器
 */
@Component
public class CrossFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("跨域请求拦截器初始化");
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Origin", "*");

        // 允许浏览器在预检请求成功之后发送的实际请求方法名
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, HEAD");
        response.setHeader("Access-Control-Allow-Methods", "*");
        // 浏览器缓存预检请求结果时间,单位:秒
        response.setHeader("Access-Control-Max-Age", "3600");

        response.setHeader("Access-Control-Expose-Headers", "token, sessionId, Authorization, sec-ch-ua, sec-ch-ua-mobile");

        //运行浏览器发送的请求头
        response.setHeader("Access-Control-Allow-Headers", "token, authorization, content-type");
        response.setHeader("Access-Control-Request-Headers", "Origin,token,authority,content-type,version-info,X-Requested-With,Authorization,Origin");

        response.setHeader("Access-Control-Allow-Credentials", "true");

        //防止乱码，用于做json数据传输
        response.setHeader("Content-Type", "application/json;charset=UTF-8");

        filterChain.doFilter(servletRequest, response);
    }
}

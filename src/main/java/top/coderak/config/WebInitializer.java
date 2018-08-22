package top.coderak.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class WebInitializer implements WebApplicationInitializer {
    //WebApplicationInitializer可以看做是Web.xml的替代
    //通过实现WebApplicationInitializer，在其中可以添加servlet，listener等，
    // 在加载Web项目的时候会加载这个接口实现类，从而起到web.xml相同的作用。
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MyMVCConfig.class);
        servletContext.addFilter("encodingFilter", new CharacterEncodingFilter("UTF-8",true));
        ctx.setServletContext(servletContext);
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }

}

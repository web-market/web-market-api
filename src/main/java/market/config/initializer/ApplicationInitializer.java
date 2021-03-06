package market.config.initializer;

import market.config.*;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebMvcConfig.class);
        applicationContext.register(EmailConfig.class);
        applicationContext.register(DatabaseConfig.class);
        applicationContext.register(EncryptionConfig.class);
        applicationContext.register(L10nConfig.class);
        //applicationContext.register(WebSecurityConfig.class);
        applicationContext.register(CloudinaryConfig.class);
        servletContext.addListener(new ContextLoaderListener(applicationContext));

        applicationContext.setServletContext(servletContext);

        ServletRegistration.Dynamic dispatcher = servletContext
                .addServlet("dispatcher", new DispatcherServlet(applicationContext));
        dispatcher.setAsyncSupported(true);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

}

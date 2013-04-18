package mock.rest.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler('/view/**').addResourceLocations('/view/')
        registry.addResourceHandler('/script/**').addResourceLocations('/script/')
    }
}

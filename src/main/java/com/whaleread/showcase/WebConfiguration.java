package com.whaleread.showcase;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import com.github.jknack.handlebars.springmvc.SpringTemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dolphin
 */
@Configuration
public class WebConfiguration {
    @Configuration
    public static class WebMvcConfiguration implements WebMvcConfigurer {
        @Autowired
        private ApplicationContext applicationContext;
        @Autowired
        private Handlebars handlebars;
        @Autowired
        private Map<String, Helper<?>> helperMap;

        @Bean
        public HandlebarsViewResolver handlebarsViewResolver() {
            HandlebarsViewResolver viewResolver = new HandlebarsViewResolver(handlebars);
//            Map<String, Helper<?>> helpers = new HashMap<>(helperMap.size());
//            for (Map.Entry<String, Helper<?>> entry : helperMap.entrySet()) {
//                if (entry.getKey().endsWith("Helper")) {
//                    helpers.put(entry.getKey().substring(0, entry.getKey().length() - 6), entry.getValue());
//                } else {
//                    helpers.put(entry.getKey(), entry.getValue());
//                }
//            }
            viewResolver.setHelpers(helperMap);
            // no cache for dev
            viewResolver.setCache(!applicationContext.getEnvironment().acceptsProfiles("dev"));

            viewResolver.setBindI18nToMessageSource(true);

            return viewResolver;
        }

        @Override
        public void configureViewResolvers(ViewResolverRegistry registry) {
            registry.viewResolver(handlebarsViewResolver());
        }
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public Handlebars handlebars() {
        Handlebars handlebars = new Handlebars(templateLoader());
        return handlebars;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.mvc.view")
    public SpringTemplateLoader templateLoader() {
        return new SpringTemplateLoader(applicationContext);
    }

}

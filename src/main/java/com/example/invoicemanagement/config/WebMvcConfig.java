package com.example.invoicemanagement.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebMvcConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
        CorsConfiguration config = new CorsConfiguration();

        // Choose ONE strategy below:
        // 1) Wide-open (no credentials): simplest during development
        config.setAllowedOriginPatterns(List.of("http://localhost:*", "https://*.example.com"));
        // If you prefer exact origins: config.setAllowedOrigins(List.of("http://localhost:3000", "https://app.example.com"));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD"));

        // Let browser send whatever it needs; preflight succeeds by echoing requested headers
        config.addAllowedHeader("*");
        // Expose any headers the client needs to read (add as necessary)
        config.setExposedHeaders(List.of("Location"));

        // Keep false unless you really need cookies/HTTP auth; if true, do NOT use "*" origins
        config.setAllowCredentials(false);

        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        CorsFilter corsFilter = new CorsFilter(source);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(corsFilter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE); // ensure runs before other filters/DispatcherServlet

        return bean;
    }
}

package com.jfsd.moviebooking.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties(prefix = "movieadmin-config")
public class ApplicationConfig {

    private String movieAdminUrl;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MovieAdminProperties restTemplateProperties() {
        return new MovieAdminProperties(movieAdminUrl, restTemplate());
    }

    public String getMovieAdminUrl() {
        return movieAdminUrl;
    }

    public void setMovieAdminUrl(String movieAdminUrl) {
        this.movieAdminUrl = movieAdminUrl;
    }

    public static class MovieAdminProperties {

        private final String url;
        private final RestTemplate restTemplate;

        public MovieAdminProperties(String url, RestTemplate restTemplate) {
            this.url = url;
            this.restTemplate = restTemplate;
        }

        public String getUrl() {
            return url;
        }

        public RestTemplate getRestTemplate() {
            return restTemplate;
        }
    }
}


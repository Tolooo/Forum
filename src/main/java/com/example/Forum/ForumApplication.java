package com.example.Forum;

import com.example.Forum.configs.RootConfig;
import com.example.Forum.configs.SecurityConfig;
import com.example.Forum.configs.ServletInitializer;
import com.example.Forum.configs.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({SecurityConfig.class, ServletInitializer.class, WebConfig.class, RootConfig.class})
public class ForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }
}

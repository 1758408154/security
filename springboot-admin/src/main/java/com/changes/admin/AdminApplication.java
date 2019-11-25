package com.changes.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LiuJunJie
 * @since 2019/11/21 14:36
 */
@SpringBootApplication(scanBasePackages = "com.changes")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class);
    }
}

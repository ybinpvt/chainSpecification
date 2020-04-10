package com.ybin.support.annotations;

import lombok.Data;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableJpaAuditing
@EnableJpaRepositories
@EnableScheduling
@EnableCaching
@SpringBootApplication
public @interface SpringBootWebApplication {
}

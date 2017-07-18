package com.netikras.studies.studentbuddy.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by netikras on 17.6.21.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.netikras.studies.studentbuddy.api"})
public class ApiConfig {



}

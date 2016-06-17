package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import config.MailConfig;


@Configuration
@Import({MailConfig.class})
@ComponentScan(ResourceNames.REST_API)
public class TestsMailConfig {

}

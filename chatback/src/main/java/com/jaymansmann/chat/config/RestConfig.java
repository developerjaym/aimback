/**
 * 
 */
package com.jaymansmann.chat.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
/**
 * @author Jay
 *
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder()
				.build();
	}
}
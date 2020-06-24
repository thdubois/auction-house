package com.auction.house.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.auction.house.utils.StringToAuctionStatusConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToAuctionStatusConverter());
    }
}
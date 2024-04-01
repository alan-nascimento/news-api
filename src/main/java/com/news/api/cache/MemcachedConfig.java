package com.news.api.cache;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@Configuration
public class MemcachedConfig {
    @Value("${memcached.url}")
    String memcachedUrl;

    @Bean
    public MemcachedClient memcachedClient() throws IOException {
        XMemcachedClientBuilder builder = new XMemcachedClientBuilder(memcachedUrl);
        return builder.build();
    }
}

package gr.scytalys.team3.Technikon.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("PropertyOwners");
        cacheManager.setCacheNames(Collections.singleton("Properties"));
        cacheManager.setCacheNames(Collections.singleton("PropertyRepairs"));
        return cacheManager;
    }
}

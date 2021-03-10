package com.nfc.cloudeureka;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.ws.rs.Path;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableEurekaServer
public class CloudEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudEurekaApplication.class, args);
    }


    /**
     * 模拟eureka的缓存
     */
    LoadingCache<String, String > cache = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    return "load:" + new Random().nextInt(100);
                }
            });

    @PostMapping("/testset/{id}")
    public String testSet(@PathVariable("id") String id ){
        cache.put(id,"set:" + new Random().nextInt(100));
        return "success !" ;
    }

    @GetMapping("testget/{id}")
    public String testGet (@PathVariable("id")String id ) throws ExecutionException {
        return cache.get(id).toString();
    }
}

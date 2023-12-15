package com.example.demo.controller;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author 张旭康
 * @Date 2022/9/9
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    // @GetMapping("/t")
    // public String test(String name) {
    //     return name + name;
    // }

    @GetMapping("/t")
    public String test1(String name) throws Exception {
        String s = testService.asyncMethod();
        if (!"123".equals(s)) {
            return s;
        }
        return "";
    }

    @GetMapping("/tt")
    public String test01() {
        JSONObject object = new JSONObject();
        object.put("token", "43c767cdb51545149565c013b2ec1707");
        String body = HttpRequest.post("https://www.zmcc-jiling.com/wxs/api/v1/sso/auth/token_auth")
                .header("appkey", "6de79b80a7414b94ac12837453b8d6f9")
                .header("timestamp", "20230111152059")
                .header("signature", "4aed1a2eb5699fd3")
                .header("Content-Type", "application/json")
                .body(object.toJSONString())
                .execute().body();
        return body;
    }

    public void aysncMethod() throws InterruptedException {
        Thread.sleep(3000);
        log.info("AsyncMethod..." + new Date());
    }

    @GetMapping("/t123")
    public String test02() {
        for (int i = 0; i < 10000; i++) {
            testService.handleTask();
        }
        return "success";
    }

    @GetMapping("/t456")
    public String test03() {
        List<String> list = new ArrayList<>();
        try {
            for (int i = 0; i < 10000; i++) {
                CompletableFuture<List<String>> listCompletableFuture = testService.handleTaskWithReturnValue(i);
                list.addAll(listCompletableFuture.get());
            }
            return list.toString();
        } catch (InterruptedException | ExecutionException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/posttest")
    public String test04(@RequestBody JSONObject object,
                         @RequestParam("aa") String aa) {
        System.out.println(object.toJSONString());
        System.out.println(aa);
        return object.toJSONString() + "\n" + aa;
    }

    @GetMapping("/threadTest")
    public String test05(String aa) {
        return testService.test05(aa).toString();
    }

    @PostMapping("/file")
    public String file(MultipartFile file, String name) {
        System.out.println(file.getOriginalFilename());
        return name;
    }

    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("token", "43c767cdb51545149565c013b2ec1707");
        String body = HttpRequest.post("https://www.zmcc-jiling.com/wxs/api/v1/sso/auth/token_auth")
                .header("appkey", "6de79b80a7414b94ac12837453b8d6f9")
                .header("timestamp", "20230111152059")
                .header("signature", "4aed1a2eb5699fd3")
                .header("Content-Type", "application/json")
                .body(object.toJSONString())
                .execute().body();
        System.out.println(body);
    }

}

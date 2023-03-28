package com.minog.minog.controller;

import com.minog.minog.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class PostController {

    // SSR -> jsp, thymeleaf, mustache, freemarker
            // -> html rendering
    // SPA ->
    // vue -> vue+SSR = nuxt.js
    // react -> react+SSR -> next.js
           // -> javascript <-> API (JSON)

    // Http Method
    // GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT
    // 글 등록
    // POST Method

    @PostMapping("/posts")
    public String post(@ModelAttribute PostCreate params) {
        log.info("params={}", params.toString());

        return "Hello world";
    }

}

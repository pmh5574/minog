package com.minog.minog.controller;

import com.minog.minog.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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
    public Map<String, String> post(@RequestBody @Valid PostCreate params, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField(); // title
            String errorMessge = firstFieldError.getDefaultMessage(); // ..에러 메세지

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessge);
            return error;

        }
        log.info("params={}", params.toString());
        return Map.of();
    }

}

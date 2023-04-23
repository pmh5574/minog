package com.minog.minog.controller;

import com.minog.minog.request.PostCreate;
import com.minog.minog.request.PostEdit;
import com.minog.minog.request.PostSearch;
import com.minog.minog.response.PostResponse;
import com.minog.minog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
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

    private final PostService postService;

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request, @RequestHeader String authorization) {
        // Case1. 저장한 데이터 Entity -> response로 응답하기
        // Case2. 저장한 데이터 primary_id -> response로 응답하기
        //          Client에서는 수신한 id를 글 조회 API를 통해서 데이터를 수신받음
        // Case3. 응답 필요 없음 -> Client에서 모든 POST(글) 데이터 context를 잘 관리함

        if (authorization.equals("mino")) {
            request.validate();
            postService.write(request);
        }

    }

    /**
     * /posts -> 글 전체 조회(검색 + 페이징)
     * /posts/{postId} -> 글 한개만 조회
     */

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request, @RequestHeader String authorization) {
        if (authorization.equals("mino")) {
            postService.edit(postId, request);
        }

    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId, @RequestHeader String authorization) {
        if (authorization.equals("mino")) {
            postService.delete(postId);
        }

    }
}

package com.minog.minog.service;

import com.minog.minog.domain.Post;
import com.minog.minog.repository.PostRepository;
import com.minog.minog.request.PostCreate;
import com.minog.minog.request.PostSearch;
import com.minog.minog.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {
        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();

        postRepository.save(post);

    }

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public List<PostResponse> getList(PostSearch postSearch) {

        return postRepository.getList(postSearch).stream()
//                .map(post -> new PostResponse(post)) // 위 아래 동일
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }
}

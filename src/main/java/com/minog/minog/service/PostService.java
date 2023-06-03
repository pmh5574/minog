package com.minog.minog.service;

import com.minog.minog.config.JpaInterceptor;
import com.minog.minog.domain.Post;
import com.minog.minog.domain.PostEditor;
import com.minog.minog.exception.PostNotFound;
import com.minog.minog.repository.PostRepository;
import com.minog.minog.request.PostCreate;
import com.minog.minog.request.PostEdit;
import com.minog.minog.request.PostSearch;
import com.minog.minog.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final EntityManagerFactory entityManagerFactory;

    public void write(PostCreate postCreate) {
        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();

        postRepository.save(post);

    }

    public PostResponse get(Long id) {


        // Hibernate 구성 파일을 로드하여 세션 팩토리를 생성합니다.
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // 인터셉터가 등록된 세션을 생성합니다.
        Session session = sessionFactory.openSession();
        Session interceptedSession = session.getSessionFactory().withOptions().interceptor(new JpaInterceptor()).openSession();

        Post post = interceptedSession.get(Post.class, id);

        if (post == null) {
            throw new PostNotFound();
        }

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
//        Post post = postRepository.findById(id)
//                .orElseThrow(PostNotFound::new);
//
//        return PostResponse.builder()
//                .id(post.getId())
//                .title(post.getTitle())
//                .content(post.getContent())
//                .build();
    }

    public List<PostResponse> getList(PostSearch postSearch) {

        return postRepository.getList(postSearch).stream()
//                .map(post -> new PostResponse(post)) // 위 아래 동일
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void edit(Long id, PostEdit postEdit) {
        // Transactional이 save를 달고 있음
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        PostEditor.PostEditorBuilder editorBuilder = post.toEditor();

        PostEditor postEditor = editorBuilder.title(postEdit.getTitle())
                .content(postEdit.getContent())
                .build();

        post.edit(postEditor);

    }

    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        postRepository.delete(post);
    }
}

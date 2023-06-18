package com.minog.minog.repository;

import com.minog.minog.domain.Post;
import com.minog.minog.request.PostSearch;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.minog.minog.domain.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositroyCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getList(PostSearch postSearch) {
        // Qpost not found..
        return jpaQueryFactory.selectFrom(post)
                .limit(postSearch.getSize())
                .offset(postSearch.getOffset())
                .orderBy(post.id.desc())
                .fetch();
    }
}

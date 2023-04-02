package com.minog.minog.repository;

import com.minog.minog.domain.Post;
import com.minog.minog.request.PostSearch;

import java.util.List;

public interface PostRepositroyCustom {

    List<Post> getList(PostSearch postSearch);

}

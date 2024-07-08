package com.Website.WebBanHang.Service;

import com.Website.WebBanHang.model.Blog;
import com.Website.WebBanHang.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogService {
    @Autowired
    private final BlogRepository blogRepository;
    public List<Blog> findAllBlogs(){
        return blogRepository.findByHideTrueOrderByOrderAsc();
    }
}

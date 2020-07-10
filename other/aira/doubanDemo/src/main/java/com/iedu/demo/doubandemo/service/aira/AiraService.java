package com.iedu.demo.doubandemo.service.aira;

import com.iedu.demo.doubandemo.entity.aira.Comment;

import java.util.List;

public interface AiraService {
    List<Comment> search(int page, int limit);
}

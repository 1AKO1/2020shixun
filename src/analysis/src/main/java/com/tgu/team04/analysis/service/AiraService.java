package com.tgu.team04.analysis.service;

import com.tgu.team04.analysis.entity.AiraComment;

import java.util.List;

public interface AiraService {
    List<AiraComment> search(int page, int limit);
}

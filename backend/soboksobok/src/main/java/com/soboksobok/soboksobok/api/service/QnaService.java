package com.soboksobok.soboksobok.api.service;

import com.soboksobok.soboksobok.api.entity.dto.QnaDto;

import java.util.List;

public interface QnaService {
    List<QnaDto> getAllQna();
//    List<QnaDto> getAllMyQna();
    QnaDto getQnaDetail(Long qna_id);
}

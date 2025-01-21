package com.ieltsdemo.service;

import com.ieltsdemo.dto.server.UploadTextWithQuestionsDTO;

public interface UploadService {
    void uploadTextWithQuestions(UploadTextWithQuestionsDTO dto);
    void updateTextWithQuestions(String textId, UploadTextWithQuestionsDTO dto);

}

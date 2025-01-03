package com.ieltsdemo.service.impl;

import com.ieltsdemo.dto.UploadTextDTO;
import com.ieltsdemo.model.Text;
import com.ieltsdemo.model.question.ClosedQuestion;
import com.ieltsdemo.model.question.OpenQuestion;
import com.ieltsdemo.model.question.Question;
import com.ieltsdemo.repository.QuestionRepository;
import com.ieltsdemo.repository.TextRepository;
import com.ieltsdemo.service.UploadService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

    private final TextRepository textRepository;
    private final QuestionRepository questionRepository;

    public UploadServiceImpl(TextRepository textRepository, QuestionRepository questionRepository) {
        this.textRepository = textRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public void uploadTextAndQuestions(UploadTextDTO uploadTextDTO) {
        // Создаем текст
        Text text = new Text();
        text.setTitle(uploadTextDTO.getTitle());
        text.setContent(uploadTextDTO.getContent());
        text.setTestName(uploadTextDTO.getTestName());
        text.setSection(uploadTextDTO.getSection());
        text.setTextNumber(uploadTextDTO.getTextNumber());
        text.setExamType(uploadTextDTO.getExamType());

        // Сохраняем текст
        Text savedText = textRepository.save(text);

        // Сохраняем вопросы
        List<Question> questions = uploadTextDTO.getQuestions().stream().map(q -> {
            Question question;
            if ("CLOSED".equalsIgnoreCase(q.getType())) {
                ClosedQuestion closedQuestion = new ClosedQuestion();
                closedQuestion.setOptions(q.getOptions());
                question = closedQuestion;
            } else {
                question = new OpenQuestion();
            }
            question.setNum(q.getNum());
            question.setText(q.getText());
            question.setCorrectAnswer(q.getCorrectAnswer());
            question.setTip(q.getTip());
            question.setRelatedTextId(savedText.getId());
            question.setTechnicalDetails(q.getTechnicalDetails());
            question.setType(q.getType());
            return question;
        }).toList();

        questionRepository.saveAll(questions);
    }
}

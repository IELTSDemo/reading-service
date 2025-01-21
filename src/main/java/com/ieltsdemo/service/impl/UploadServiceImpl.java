package com.ieltsdemo.service.impl;

import com.ieltsdemo.dto.server.UploadTextWithQuestionsDTO;
import com.ieltsdemo.model.text.Text;
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
    public void uploadTextWithQuestions(UploadTextWithQuestionsDTO dto) {
        // Создаем текст
        Text text = new Text();
        text.setIntroduction(dto.getIntroduction());
        text.setTitle(dto.getTitle());
        text.setContent(dto.getContent());
        text.setSection(dto.getSection());
        text.setTextNumber(dto.getTextNumber());
        text.setTestId(dto.getTestId());


        // Сохраняем текст
        Text savedText = textRepository.save(text);

        // Создаем и сохраняем вопросы
        List<Question> questions = dto.getQuestions().stream().map(q -> {
            Question question;
            if ("CLOSED".equalsIgnoreCase(q.getType().getValue())) {
                ClosedQuestion closedQuestion = new ClosedQuestion();
                closedQuestion.setOptions(q.getOptions());
                question = closedQuestion;
            } else {
                question = new OpenQuestion();
            }
            question.setNum(q.getNum());
            question.setQuestion(q.getText());
            question.setCorrectAnswer(q.getCorrectAnswer());
            question.setTipParagraph(q.getTip());
            question.setRelatedTextId(savedText.getId());
            question.setTechnicalDetails(q.getTechnicalDetails());
            question.setType(q.getType());
            question.setExplanation(q.getExplanation());
            return question;
        }).toList();

        questionRepository.saveAll(questions);

    }

        @Override
        public void updateTextWithQuestions(String textId, UploadTextWithQuestionsDTO dto) {
            // 1. Находим существующий текст в БД
            Text existingText = textRepository.findById(textId)
                    .orElseThrow(() -> new RuntimeException("Text not found: " + textId));

            // 2. Обновляем поля текста
            existingText.setIntroduction(dto.getIntroduction());
            existingText.setTitle(dto.getTitle());
            existingText.setContent(dto.getContent());
            existingText.setSection(dto.getSection());
            existingText.setTextNumber(dto.getTextNumber());
            existingText.setTestId(dto.getTestId()); // если надо менять testId

            // 3. Сохраняем изменения в тексте
            Text savedText = textRepository.save(existingText);

            // 4. Удаляем (или обновляем) старые вопросы
            //    — самый простой путь: удалить все вопросы, связанные с этим текстом.
            questionRepository.deleteQuestionByRelatedTextId(textId);

            // 5. Создаём новые вопросы из dto (аналогично uploadTextWithQuestions)
            List<Question> questions = dto.getQuestions().stream().map(q -> {
                Question question;
                if ("CLOSED".equalsIgnoreCase(q.getType().getValue())) {
                    ClosedQuestion closedQuestion = new ClosedQuestion();
                    closedQuestion.setOptions(q.getOptions());
                    question = closedQuestion;
                } else {
                    question = new OpenQuestion();
                }
                question.setNum(q.getNum());
                question.setQuestion(q.getText());
                question.setCorrectAnswer(q.getCorrectAnswer());
                question.setTipParagraph(q.getTip());
                question.setRelatedTextId(savedText.getId());
                question.setTechnicalDetails(q.getTechnicalDetails());
                question.setType(q.getType());
                question.setExplanation(q.getExplanation());
                return question;
            }).toList();

            questionRepository.saveAll(questions);
        }
}

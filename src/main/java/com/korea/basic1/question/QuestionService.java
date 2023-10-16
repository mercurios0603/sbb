package com.korea.basic1.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.korea.basic1.DataNotFoundException;

@RequiredArgsConstructor
@Service
public class QuestionService {
  private final QuestionRepository questionRepository;

  public List<QuestionDTO> getList() {
    List<Question> questions = this.questionRepository.findAll();
    List<QuestionDTO> questionDTOs = new ArrayList<>();

    for (Question question : questions) {
      QuestionDTO dto = new QuestionDTO();
      dto.setId(question.getId());
      dto.setSubject(question.getSubject());
      dto.setCreateDate(question.getCreateDate());
      questionDTOs.add(dto);
    }

    return questionDTOs;
  }

  // 위의 getList는 DTO를 사용한 것. 아래는 엔티티 객체를 직접 호출한 것. (챗 GPT가 알려준대로 하긴 했는데 잘 되는지 의문임)
  public Question getQuestion(Integer id) {
    Optional<Question> question = this.questionRepository.findById(id);
    if (question.isPresent()) {
      return question.get();
    } else {
      throw new DataNotFoundException("question not found");
    }
  }
  public void create(String subject, String content) {
    Question q = new Question();
    q.setSubject(subject);
    q.setContent(content);
    q.setCreateDate(LocalDateTime.now());
    this.questionRepository.save(q);
  }
}

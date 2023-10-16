package com.korea.basic1.question;

import java.util.List;

import com.korea.basic1.answer.AnswerForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

  private final QuestionService questionService;

  @GetMapping("/list")
  public String list(Model model) {
    List<QuestionDTO> questionList = this.questionService.getList();
    model.addAttribute("questionList", questionList);
    return "question_list";
  }

  @GetMapping(value = "/detail/{id}")
  public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
    Question question = this.questionService.getQuestion(id);
    model.addAttribute("question", question);
    return "question_detail";
  }

  @GetMapping("/create")
  public String questionCreate(QuestionForm questionForm) {
    return "question_form";
  }

  @PostMapping("/create")
  public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "question_form";
    }
    this.questionService.create(questionForm.getSubject(), questionForm.getContent());
    return "redirect:/question/list";
  }
}

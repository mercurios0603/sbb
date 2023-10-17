package com.korea.basic1.answer;

import java.time.LocalDateTime;
import java.util.Set;

import com.korea.basic1.question.Question;
import com.korea.basic1.user.SiteUser;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@Entity
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(columnDefinition = "TEXT")
  private String content;

  @CreatedDate
  private LocalDateTime createDate;

  @ManyToOne
  private Question question;

  @ManyToOne
  private SiteUser author;

  @CreatedDate
  private LocalDateTime modifyDate;

  @ManyToMany
  Set<SiteUser> voter;
}

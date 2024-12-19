package org.software_assignment.lms.controller;

import org.software_assignment.lms.entity.QuizEntity;
import org.software_assignment.lms.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // Display a quiz
    @GetMapping("/{quizId}")
    public QuizEntity getQuiz(@PathVariable int quizId) {
        return quizService.getQuizById(quizId);
    }

    // Add student answers
    @PostMapping("/{quizId}/submit")
    public String submitAnswers(@PathVariable int quizId, @RequestParam int studentId, @RequestBody Map<String, String> answers) {
        quizService.addStudentAnswers(quizId, studentId, answers);
        return "Answers submitted successfully!";
    }

    // Grade the quiz
    @GetMapping("/{quizId}/grade")
    public Map<String, Object> gradeQuiz(@PathVariable int quizId, @RequestParam int studentId) {
        return quizService.gradeQuiz(quizId, studentId);
    }
}

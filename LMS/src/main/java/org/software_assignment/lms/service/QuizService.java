package org.software_assignment.lms.service;

import org.software_assignment.lms.entity.*;
import org.software_assignment.lms.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    // Display a quiz by ID
    public QuizEntity getQuizById(int quizId) {
        return quizRepository.findById(quizId);
    }

    // Add a student's answers to the quiz
    public void addStudentAnswers(int quizId, int studentId, Map<String, String> answers) {
        QuizEntity quiz = quizRepository.findById(quizId);
        if (quiz != null) {
            quiz.getStudentAnswers().put(studentId, answers);
            quizRepository.save(quiz);
        }
    }

    // Grade the quiz
    public Map<String, Object> gradeQuiz(int quizId, int studentId) {
        QuizEntity quiz = quizRepository.findById(quizId);
        if (quiz == null) {
            return Map.of("error", "Quiz not found");
        }

        Map<String, String> studentAnswers = quiz.getStudentAnswers().get(studentId);
        if (studentAnswers == null) {
            return Map.of("grade", 0, "feedback", "No answers found for this student.");
        }

        Map<String, String> modelAnswers = quiz.getModelAnswer();
        int score = 0;

        for (Map.Entry<String, String> entry : modelAnswers.entrySet()) {
            String question = entry.getKey();
            String correctAnswer = entry.getValue();
            String studentAnswer = studentAnswers.getOrDefault(question, "");

            if (correctAnswer.equalsIgnoreCase(studentAnswer)) {
                score++;
            }
        }

        int totalQuestions = modelAnswers.size();
        quiz.addstudentGrades(studentId,score);
        return Map.of(
                "grade", score,
                "totalQuestions", totalQuestions,
                "feedback", "You scored " + score + " out of " + totalQuestions
        );
    }

    public void submitQuiz(int quizId, int studtId, Map<String, String> answer) throws Exception {

        QuizEntity quiz = quizRepository.findById(quizId);
        if(quiz!=null) {
            quiz.addStudentAnswer(studtId, answer);
            gradeQuiz(quizId,studtId);
        }
        else throw new Exception("Quiz not found");
    }

    public String trackQuiz(int quizId) {
        QuizEntity quiz = getQuizById(quizId);
        Map<Integer,Integer> scores = quiz.getStudentGrades();;
        String output ="";
        for(Map.Entry<Integer,Integer>score:scores.entrySet()){
            output += "Student id: "+score.getKey() + " | Grade: "+score.getValue()+"\n";
        }
        return  output;
    }

    public double averageScore(int quizId){

        QuizEntity quiz = getQuizById(quizId);
        if(quiz!=null) {
            Map<Integer, Integer> scores = quiz.getStudentGrades();
            ;
            double sum = 0;
            for (Map.Entry<Integer, Integer> score : scores.entrySet()) {
                sum += score.getValue();
            }
            return sum / quiz.getStudentGrades().size();
        }
        return  0.0;

    }
}

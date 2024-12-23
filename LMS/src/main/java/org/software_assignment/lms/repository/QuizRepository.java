
package org.software_assignment.lms.repository;
import org.springframework.stereotype.Repository;
import org.software_assignment.lms.entity.QuizEntity;
import java.util.*;
@Repository
public class QuizRepository {

    private final List<QuizEntity> quizes = new ArrayList<>();

    public QuizRepository() {
        // Hardcoded data from CourseService
        quizes.add(new QuizEntity(
                1, "Quiz 1", "1", 3,
                Map.of(
                        "What is Java?", "A programming language",
                        "What is JVM?", "Java Virtual Machine",
                        "Explain OOP concepts in Java", "Inheritance, Polymorphism, Encapsulation, Abstraction"
                )
        ));
        quizes.add(new QuizEntity(
                2, "Quiz 2", "1", 3,
                Map.of(
                        "What are Java data types?", "Primitive and Non-Primitive",
                        "Explain the use of 'final' keyword", "To define constants or prevent inheritance",
                        "What is the purpose of garbage collection?", "To free up unused memory"
                )
        ));
    }

    public List<QuizEntity> findAll() {
        return new ArrayList<>(quizes);
    }

    public QuizEntity findById(int id) {
        return quizes.stream()
                .filter(quiz -> quiz.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean deleteById(int id) {
        return quizes.removeIf(quiz -> quiz.getId() == id);
    }

    public QuizEntity save(QuizEntity quiz) {
        deleteById(quiz.getId());
        quizes.add(quiz);
        return quiz;
    }
}

package org.software_assignment.lms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.software_assignment.lms.entity.AssignmentEntity;
import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.repository.AssignmentRepo;
import org.software_assignment.lms.repository.CourseRepository;
import org.software_assignment.lms.service.AssignmentService;
import org.software_assignment.lms.service.CourseService;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;



class AssignmentServiceTest {

    @Mock
    private AssignmentRepo assignmentRepo;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CourseService courseService;

    @InjectMocks
    private AssignmentService assignmentService;

    private AssignmentEntity assignment;
    private CourseEntity course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Set up Course Entity mock
        course = new CourseEntity();
        course.setId("course1");
        course.setTitle("Test Course");

        // Set up AssignmentEntity mock
        assignment = new AssignmentEntity();
        assignment.setAssignmentID(1);
        assignment.setCourseId("course1");
        assignment.setAssignmentName("Test Assignment");
        assignment.setAssignmentDescription("Test Description");
        assignment.setDueDate("2024-12-31");
        assignment.setFilePath("path/to/assignment/file");
    }

    @Test
    void testAddStudentSubmission() {
        // Set up mock behavior
        when(assignmentRepo.findById(1)).thenReturn(assignment);

        // Call the method
        assignmentService.addStudentSubmission(123, 1, "path/to/submission");

        // Verify that the submission was added
        assertTrue(assignment.getStudentSubmission().containsKey(123));
        assertEquals("path/to/submission", assignment.getStudentSubmission().get(123));

        // Test submission after due date
        LocalDate pastDate = LocalDate.now().minusDays(1);
        assignment.setDueDate(pastDate.toString());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            assignmentService.addStudentSubmission(123, 1, "path/to/submission");
        });
        assertEquals("Cannot submit the assignment. The due date has passed.", exception.getMessage());
    }

    @Test
    void testGradeAssignment() {
        // Set up mock behavior
        assignment.addSubmission(123, "path/to/submission");
        assignment.addStudentsGrades(123, 90.0);
        when(assignmentRepo.findById(1)).thenReturn(assignment);

        // Call the method
        String feedback = assignmentService.gradeAssignment(123, 1);

        // Verify the feedback (submission path)
        assertEquals("path/to/submission", feedback);

        // Test when student hasn't submitted the assignment
        String feedbackNoSubmission = assignmentService.gradeAssignment(999, 1);
        assertEquals("Student hasn't submitted assignment", feedbackNoSubmission);
    }

    @Test
    void testGetAssignmentFilePath() {
        // Set up mock behavior
        when(assignmentRepo.findById(1)).thenReturn(assignment);

        // Call the method
        String filePath = assignmentService.getAssignmentFilePath(1);

        // Verify file path
        assertEquals("path/to/assignment/file", filePath);

        // Test for non-existing assignment
        when(assignmentRepo.findById(2)).thenReturn(null);
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            assignmentService.getAssignmentFilePath(2);
        });
        assertEquals("Assignment not found", exception.getMessage());
    }



    @Test
    void testGetAssignment() {
        // Set up a mock behavior where assignmentRepo.findById(1) returns a mock AssignmentEntity
        AssignmentEntity mockAssignment = new AssignmentEntity(1, "Assignment 1", "Description", "2024-12-31", "path/to/file", "course1");
        when(assignmentRepo.findById(1)).thenReturn(mockAssignment);  // This will return a valid assignment for ID 1

        // Call the method for the existing assignment
        AssignmentEntity result = assignmentService.getAssigment(1);

        assertNotNull(result);
        assertEquals(1, result.getAssignmentID());
        assertEquals("Assignment 1", result.getAssignmentName());

        // Test for non-existing assignment with ID 2
        when(assignmentRepo.findById(2)).thenReturn(null);  // Mocking null for non-existent assignment

        // Call the method for a non-existing assignment and check the result
        AssignmentEntity result2 = assignmentService.getAssigment(2);

        // Verify that the result is null for non-existing assignment
        assertNull(result2);  // Since the findById returns null, we assert that the result is null
    }

    @Test
    void testAddStudentGrade() {
        // Set up mock behavior
        when(assignmentRepo.findById(1)).thenReturn(assignment);

        // Call the method
        assignmentService.addStudentGrade(1, 123, 95.0);

        // Verify the grade
        assertEquals(95.0, assignment.getStudentsGrades().get(123));
    }

    @Test
    void testGetFeedback() {
        // Set up mock behavior
        when(assignmentRepo.findById(1)).thenReturn(assignment);
        assignment.addStudentsGrades(123, 90.0);

        // Call the method
        String feedback = assignmentService.getFeedback(1, 123);

        // Verify feedback
        assertEquals("Your feedback: 90.0", feedback);

        // Test when assignment is not graded
        assignment.getStudentsGrades().clear();
        String noGradeFeedback = assignmentService.getFeedback(1, 123);
        assertEquals("Assigment is not graded", noGradeFeedback);
    }


}

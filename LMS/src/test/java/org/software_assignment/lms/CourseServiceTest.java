package org.software_assignment.lms;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.software_assignment.lms.entity.CourseEntity;
import org.software_assignment.lms.entity.UserEntity;
import org.software_assignment.lms.repository.CourseRepository;
import org.software_assignment.lms.service.CourseService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    private CourseEntity course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a mock course
        course = new CourseEntity();
        course.setId("1");
        course.setTitle("Course 1");
        course.setDescription("Description of Course 1");
        course.setInstructorId(101);
        course.setEnrolledStudents(new ArrayList<>());
    }

    @Test
    void testFindAll() {
        List<CourseEntity> courses = new ArrayList<>();
        courses.add(course);

        when(courseRepository.findAll()).thenReturn(courses);

        List<CourseEntity> result = courseService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Course 1", result.get(0).getTitle());
    }

    @Test
    void testDeleteStudent_Success() {
        UserEntity student = new UserEntity();
        student.setId(1001);
        course.getEnrolledStudents().add(student);

        when(courseRepository.findAll()).thenReturn(List.of(course));

        boolean result = courseService.deleteStudent("1", 1001);

        assertTrue(result);
        assertTrue(course.getEnrolledStudents().isEmpty());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testDeleteStudent_Failure_CourseNotFound() {
        when(courseRepository.findAll()).thenReturn(new ArrayList<>());

        boolean result = courseService.deleteStudent("1", 1001);

        assertFalse(result);
    }

    @Test
    void testAddCourse() {
        CourseEntity newCourse = new CourseEntity();
        newCourse.setId("2");
        newCourse.setTitle("New Course");
        newCourse.setDescription("New Course Description");
        newCourse.setInstructorId(102);

        when(courseRepository.save(any(CourseEntity.class))).thenReturn(newCourse);

        CourseEntity result = courseService.addCourse("2", "New Course", "New Course Description", 102);

        assertNotNull(result);
        assertEquals("New Course", result.getTitle());
        assertEquals("New Course Description", result.getDescription());
    }

    @Test
    void testIsStudentEnrolled_True() {
        UserEntity student = new UserEntity();
        student.setId(1001);
        course.getEnrolledStudents().add(student);

        when(courseRepository.findById("1")).thenReturn(course);

        boolean result = courseService.isStudentEnrolled("1", 1001);

        assertTrue(result);
    }

    @Test
    void testIsStudentEnrolled_False() {
        when(courseRepository.findById("1")).thenReturn(course);

        boolean result = courseService.isStudentEnrolled("1", 1002);

        assertFalse(result);
    }

    @Test
    void testDeleteCourseById_CourseNotFound() {
        when(courseRepository.findById("1")).thenReturn(null);

        assertThrows(NoSuchElementException.class, () -> courseService.deleteCourseById("1"));
    }

    @Test
    void testGetCourseDetails() {
        when(courseRepository.findById("1")).thenReturn(course);

        CourseEntity result = courseService.getCourseDetails("1");

        assertNotNull(result);
        assertEquals("Course 1", result.getTitle());
    }

    @Test
    void testAddQuestionToCourse_Success() {
        String question = "What is Java?";
        String answer = "A programming language";

        when(courseRepository.findAll()).thenReturn(List.of(course));

        course.setQuestionBank(new HashMap<>());
        String result = courseService.addQuestionToCourse("1", question, answer);

        assertEquals("Question added successfully", result);
        assertTrue(course.getQuestionBank().containsKey(question));
    }

    @Test
    void testAddQuestionToCourse_CourseNotFound() {
        String question = "What is Java?";
        String answer = "A programming language";

        when(courseRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(IllegalArgumentException.class, () -> courseService.addQuestionToCourse("1", question, answer));
    }
}

package com.clem.crudjpademo;

import com.clem.crudjpademo.dao.AppDAO;
import com.clem.crudjpademo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudJpaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudJpaDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
//            createInstructor(appDAO);
//            findInstructor(appDAO);
//            deleteInstructorById(appDAO);
//            findInstructorDetail(appDAO);
//            deleteInstructorDetailById(appDAO);
//            createInstructorWithCourses(appDAO);
//            findInstructorWithCourses(appDAO);
//            findCoursesForInstructor(appDAO);
//            findInstructorWithCoursesJoinFetcher(appDAO);
//            updateInstructor(appDAO);
//            updateCourse(appDAO);
//            deleteCourse(appDAO);
//            createCourseAndReviews(appDAO);
//            getCourseAndReviews(appDAO);
//            deleteCourseAndReviews(appDAO);
//            createStudentAndCourse(appDAO);
//            findCourseAndStudent(appDAO);
            findStudentAndCourse(appDAO);
        };
    }

    private void findStudentAndCourse(AppDAO appDAO) {
        int studentId = 1;
        System.out.println("Getting course and student with id " + studentId);
        Student student = appDAO.findStudentAndCourseByStudentId(studentId);
        System.out.println("Student: " + student);
        System.out.println("Course: " + student.getCourses());
        System.out.println("Done!");
    }

    private void findCourseAndStudent(AppDAO appDAO) {
        int courseId = 10;
        System.out.println("Getting course and student with id " + courseId);
        Course course = appDAO.findCourseAndStudentByCourseId(courseId);
        System.out.println("Course: " + course);
        System.out.println("Student: " + course.getStudents());
        System.out.println("Done!");
    }

    private void createStudentAndCourse(AppDAO appDAO) {
        Course course = new Course("Another new Course");
        Student student1 = new Student("John", "Kumah", "john@gmail.com");
        Student student2 = new Student("Jones", "Mane", "jonesn@gmail.com");
        course.addStudent(student1);
        course.addStudent(student2);

        appDAO.save(course);

        System.out.println("Done!");
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        int courseId = 10;
        System.out.println("Deleting course and reviews with id " + courseId);
        appDAO.deleteCourseById(courseId);
        System.out.println("course and reviews with id: " + courseId + " deleted");
        System.out.println("Done!");
    }

    private void getCourseAndReviews(AppDAO appDAO) {
        int courseId = 10;
        System.out.println("Getting course and reviews with id " + courseId);
        Course course = appDAO.findCourseAndReviewsById(courseId);
        System.out.println("Course: " + course);
        System.out.println("Reviews: " + course.getReviews());
        System.out.println("Done!");
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        Course course = new Course("Advanced Java");
        Instructor instructor = appDAO.findInstructorById(1);
        course.addReview(new Review("Great course..... I love it"));
        course.addReview(new Review("average"));
        course.addReview(new Review("Cool course"));
        course.addReview(new Review("Worst course ever"));
        System.out.println("Course" + course);
        System.out.println("Associated reviews " + course.getReviews());
        appDAO.save(course);
        System.out.println("Done!");
    }

    private void deleteCourse(AppDAO appDAO) {
        int courseId = 10;
        System.out.println("Deleting course with id " + courseId);
        appDAO.deleteCourseById(courseId);
        System.out.println("course with id: " + courseId + " deleted");
        System.out.println("Done!");
    }

    private void updateCourse(AppDAO appDAO) {
        int courseId = 10;
        System.out.println("Updating course with id " + courseId);
        Course course = appDAO.findCourseById(courseId);
        course.setTitle("Updated Course");
        appDAO.update(course);
        System.out.println("course with id: " + courseId + " updated");
        System.out.println("Done!");
    }

    private void updateInstructor(AppDAO appDAO) {
        int instructorId = 1;
        System.out.println("Updating instructor with id " + instructorId);
        Instructor instructor = appDAO.findInstructorById(instructorId);
//        instructor.setFirstName("Jane");
        instructor.setLastName("TESTER");
        appDAO.update(instructor);
        System.out.println("Updated instructor with id " + instructorId);
    }

    private void findInstructorWithCoursesJoinFetcher(AppDAO appDAO) {
        int instructorId = 1;
        System.out.println("Finding instructor with courses by id " + instructorId);
        Instructor instructor = appDAO.findInstructorByIdJoinFetch(instructorId);
        System.out.println("Instructor: " + instructor);
        System.out.println("Related courses " + instructor.getCourses());

        System.out.println("Done!");
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int instructorId = 1;
        System.out.println("Finding instructor with courses by id " + instructorId);
        Instructor instructor = appDAO.findInstructorById(instructorId);
        System.out.println("Instructor: " + instructor);
        System.out.println("Fetching courses");
        var courses = appDAO.findCoursesByInstructorId(instructor.getId());
        instructor.setCourses(courses);
        System.out.println("Related courses " + instructor.getCourses());
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int id = 1;
        System.out.println("Finding instructor with courses by id " + id);
        Instructor instructor = appDAO.findInstructorById(id);
        System.out.println("Instructor: " + instructor);
        System.out.println("Courses: " + instructor.getCourses());
        System.out.println("done");
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        Instructor instructor = new Instructor("John", "Doe", "john@gmail.com");
        InstructorDetail instructorDetail = new InstructorDetail(
                "https://youtube.com/john-doe",
                "Video games"
        );
        instructor.setInstructorDetail(instructorDetail);

        Course tmpCourse1 = new Course("Air Guitar - The alternate guide");
        Course tmpCourse2 = new Course("YouTube master class");

        instructor.add(tmpCourse1);
        instructor.add(tmpCourse2);

        System.out.println("Saving instructor " + instructor);
        System.out.println("The course " + instructor.getCourses());
        appDAO.save(instructor);
        System.out.println("Done!");
    }

    private void deleteInstructorDetailById(AppDAO appDAO) {
        int id = 3;
        System.out.println("Deleting instructor detail with id " + id);
        appDAO.deleteInstructorDetailById(id);
        System.out.println("Deleted instructor detail with id " + id);
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int id = 1;
        System.out.println("Finding instructor detail by id " + id);
        InstructorDetail instructorDetail = appDAO.instructorDetailById(id);
        System.out.println("Instructor Detail: " + instructorDetail);
        System.out.println("Instructor: " + instructorDetail.getInstructor());
    }

    private void deleteInstructorById(AppDAO appDAO) {
        int id = 1;
        System.out.println("Deleting instructor with id " + id);
        appDAO.deleteInstructorById(id);
        System.out.println("Deleted instructor with id " + id);
    }

    private void findInstructor(AppDAO appDAO) {
        int id = 2;
        System.out.println("Finding instructor by id " + id);
        Instructor instructor = appDAO.findInstructorById(id);
        System.out.println("Instructor: " + instructor);
        System.out.println("Instructor Detail: " + instructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {
        Instructor instructor = new Instructor("Chad", "Dary", "chad@gmail.com");
        InstructorDetail instructorDetail = new InstructorDetail(
                "https://youtube.com/chad-dary",
                "swimming"
        );
        instructor.setInstructorDetail(instructorDetail);
        System.out.println("Saving instructor: " + instructor);
        appDAO.save(instructor);
    }
}

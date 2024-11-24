package com.clem.crudjpademo.dao;

import com.clem.crudjpademo.entity.Course;
import com.clem.crudjpademo.entity.Instructor;
import com.clem.crudjpademo.entity.InstructorDetail;
import com.clem.crudjpademo.entity.Student;

import java.util.List;

public interface AppDAO {
    void save(Instructor instruction);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail instructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int instructorId);

    Instructor findInstructorByIdJoinFetch(int instructorId);

    void update(Instructor instructor);

    void update(Course course);

    Course findCourseById(int courseId);

    void deleteCourseById(int courseId);

    void save(Course course);

    Course findCourseAndReviewsById(int courseId);

    Course findCourseAndStudentByCourseId(int courseId);

    Student findStudentAndCourseByStudentId(int studentId);
}

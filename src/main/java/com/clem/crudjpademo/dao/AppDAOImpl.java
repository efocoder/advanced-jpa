package com.clem.crudjpademo.dao;

import com.clem.crudjpademo.entity.Course;
import com.clem.crudjpademo.entity.Instructor;
import com.clem.crudjpademo.entity.InstructorDetail;
import com.clem.crudjpademo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {
    private final EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instruction) {
        entityManager.persist(instruction);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        List<Course> courses = instructor.getCourses();
        for (var course : courses) {
            course.setInstructor(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail instructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);
        if (instructorDetail != null) {
            instructorDetail.getInstructor().setInstructorDetail(null);
            entityManager.remove(instructorDetail);
        }
    }

    @Override
    public List<Course> findCoursesByInstructorId(int instructorId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :instructorId", Course.class
        );
        query.setParameter("instructorId", instructorId);
        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int instructorId) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "Select i from  Instructor i "
                        + "JOIN FETCH i.courses "
                        + "JOIN FETCH i.instructorDetail "
                        + "WHERE i.id = :instructorId",
                Instructor.class
        );
        query.setParameter("instructorId", instructorId);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int courseId) {
        return entityManager.find(Course.class, courseId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int courseId) {
        var course = entityManager.find(Course.class, courseId);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsById(int courseId) {
        var query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.reviews "
                        + "WHERE c.id = :courseId",
                Course.class
        );
        query.setParameter("courseId", courseId);
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentByCourseId(int courseId) {
        var query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "WHERE c.id = :courseId",
                Course.class);
        query.setParameter("courseId", courseId);
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCourseByStudentId(int studentId) {
        var query = entityManager.createQuery(
                "select s from Student s "
                        + "JOIN FETCH s.courses "
                        + "WHERE s.id = :studentId",
                Student.class);
        query.setParameter("studentId", studentId);
        return query.getSingleResult();
    }
}

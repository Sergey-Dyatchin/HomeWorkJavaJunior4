package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class Repository {

    private final SessionFactory sessionFactory;

    public Repository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public  void addCourse(Course course) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(course);
        System.out.println("Course save successfully");
        session.getTransaction().commit();
    }

    public Course getCourse(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Course course = session.get(Course.class, id);
        System.out.println("Course read successfully");
        session.getTransaction().commit();
        return course;
    }

    public List<Course> getAllCourse() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Course> courses = session.createQuery("SELECT a FROM Course a", Course.class).getResultList();
        System.out.println("Course read all successfully");
        session.getTransaction().commit();
        return courses;
    }

    public void updateCourse (Course course) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(course);
        System.out.println("Course update successfully");
        session.getTransaction().commit();
    }

    public void deleteCourse (Course course) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(course);
        System.out.println("Course delete successfully");
        session.getTransaction().commit();
    }

}

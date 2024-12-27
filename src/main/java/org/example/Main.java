package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

/**
 * Задание
 * =====
 * Создайте базу данных (например, SchoolDB).
 * В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
 * Настройте Hibernate для работы с вашей базой данных.
 * Создайте Java-класс Course, соответствующей таблице Courses, с необходимыми аннотациями Hibernate.
 * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
 * Убедитесь что каждая операция выполняется в отдельной транзакции.
 */

public class Main {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory()) {
            Repository repositoryCourse = new Repository(sessionFactory);

            //Добавление данных
            Course course1 = new Course("базы данных", 125);
            Course course2 = new Course("программирование", 200);
            Course course3 = new Course("тестирование", 115);
            repositoryCourse.addCourse(course1);
            repositoryCourse.addCourse(course2);
            repositoryCourse.addCourse(course3);

            // Чтение данных
            System.out.println(repositoryCourse.getCourse(course2.getId()));

            List<Course> courses = repositoryCourse.getAllCourse();
            for (Course course : courses) {
                System.out.println(course);
            }

            // Обновление данных
            course2.setTitle("Програмирование 2.0");
            course2.setDuration(250);
            repositoryCourse.updateCourse(course2);

            // Чтение обновленных данных
            System.out.println(repositoryCourse.getCourse(course2.getId()));

            // Удаление данных
            for (Course course : courses) {
                repositoryCourse.deleteCourse(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
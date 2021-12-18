package com.svp;

import com.svp.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

        SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session=factory.openSession();
        try
        {
          Student newStudent=new Student("Paul","Johnson","paul.johnson@gmail.com");
          session.beginTransaction();
          session.save(newStudent);
          session.getTransaction().commit();

        }catch(Exception e)
        {
         e.printStackTrace();
        }
        finally
        {
            session.close();
        }
    }
}

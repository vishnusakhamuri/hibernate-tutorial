package com.svp;

import com.svp.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session=factory.openSession();
        try
        {
            System.out.println("Creating  Student Object/s");
            ArrayList<Student> list=new ArrayList();
            list.add( new Student("Paul","Johnson","paul.johnson@gmail.com"));
            list.add( new Student("Walter","pinto","Walter.pinto@gmail.com"));
            list.add( new Student("John","Mc arthy","John.Mcarthy@gmail.com"));
            
            
           
           // System.out.println("Saving Student's information " + newStudent.toString());
          session.beginTransaction();

          list.forEach(b -> {
              session.save(b);
              System.out.println(b.toString());
          });
          session.getTransaction().commit();

        }catch(Exception e)
        {
         e.printStackTrace();
         session.getTransaction().rollback();
        }
        finally
        {
            session.close();
            System.out.println("Successfully session was closed");
        }
    }
}

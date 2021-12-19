package com.svp;

import com.svp.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateDemo {

    public static void main(String[] args) {

        SessionFactory factory=new Configuration()
                //.configure("hibernate.cfg.xml")
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session=factory.openSession();
        try
        {
            System.out.println("Creating  Student Object/s");
            ArrayList<Student> list=new ArrayList<>();
            list.add( new Student("Paul","Johnson","paul.johnson@gmail.com"));
            list.add( new Student("Walter","pinto","Walter.pinto@gmail.com"));
            list.add( new Student("John","Mc arthy","John.Mcarthy@gmail.com"));
            
            
           
           // System.out.println("Saving Student's information " + newStudent.toString());
          session.beginTransaction();

            Session finalSession = session;
            list.forEach(b -> {
              finalSession.save(b);
              System.out.println(b.toString());
          }
          );


          session.getTransaction().commit();
          session.clear();
          //Retrieve a specific id from Data base
            session =factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Getting DB Objects from DB");
            for (Student tempStudent : list )
            {
               Student dbStudent =session.get(Student.class,tempStudent.getId());
               System.out.println( dbStudent +" and Id is "+dbStudent.getId());
            }
            session.close();
            //Retrieve a specific id from Data base
            session =factory.getCurrentSession();
            session.beginTransaction();
            Student vishnu=new Student("vishnu","gjf","vishnu.gjf@gmail.com");
            session.save(vishnu);

            //Print all the students from the table
            System.out.println("##################HQL queries##########################");
            //Select
            List<Student> studentList=session
                    .createQuery("from Student s where s.lastName='Johnson'")
                    .getResultList();
            studentList.forEach(b ->
                    System.out.println(b.toString() + " Before Changing "+ b.getId()));

            //update
            studentList.forEach(b -> b.setLastName("Johny"));
            session.createQuery("update Student set email='paul.Johny@gmail.com' where lastName='Johny'").
                    executeUpdate();
            session.getTransaction().commit();
            studentList.forEach(b -> System.out.println(b.toString() + " " + b.getId()));

            //Delete
            session =factory.getCurrentSession();
            session.beginTransaction();
            session.delete(vishnu);
            session.createQuery("delete Student where id=17 and email='John.Mcarthy@gmail.com'").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Completed");
        }catch(Exception e)
        {
         e.printStackTrace();
         session.getTransaction().rollback();
        }
        finally
        {
            factory.close();
            System.out.println("Successfully session was closed");
        }
    }
}

package com.studenttracker.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class StudentDbUtil {
    //set up reference for data source
    private DataSource dataSource;
    //create constructor
    public StudentDbUtil(DataSource theDataSource){
        dataSource = theDataSource;
    }

    public List<Student> getStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            //get a connection
            myConn = dataSource.getConnection();
            //create sql statement
            String sql = "select * from student order by last_name";
            myStmt = myConn.createStatement();
            //execute query
            myRs = myStmt.executeQuery(sql);

            //process result set
            while(myRs.next()){
                //retrieve data from result set row
                int id = myRs.getInt("id");
                String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");
                //create new student object
                Student tempStudent = new Student(id, firstName, lastName, email);
                //add to list of students
                students.add(tempStudent);
            }
            return students;
        }finally {
            close(myConn, myStmt, myRs);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myRs != null){
                myRs.close();
            }
            if (myConn != null){
                myConn.close();
            }
            if (myStmt != null){
                myStmt.close();
            }
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }

    public void addStudent(Student theStudent) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "insert into student  "
                    + "(first_name, last_name, email) "
                    + "values (?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, theStudent.getFirstName());
            myStmt.setString(2, theStudent.getLastName());
            myStmt.setString(3, theStudent.getEmail());

            myStmt.execute();
        }
        finally {
            close(myConn, myStmt, null);
        }
    }

    public Student getStudent(String theStudentId) throws Exception {
        Student theStudent = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int studentId;

        try {
            //convert student id to int
            studentId = Integer.parseInt(theStudentId);

            //get connection to db
            myConn = dataSource.getConnection();

            //create sql
            String sql = "select * from student where id=?";

            //create prepared statement
            myStmt = myConn.prepareStatement(sql);

            //set params
            myStmt.setInt(1, studentId);
            //execute statement
            myRs = myStmt.executeQuery();
            //retrieve data from result set row
            if(myRs.next()){
                int id = myRs.getInt("id");
                String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");

                theStudent = new Student(id, firstName, lastName, email);
            } else {
                throw new Exception("Couldn't find student id:" + studentId);
            }
            return theStudent;
        }
        finally {
            close(myConn, myStmt, myRs);
        }


    }

    public void updateStudent(Student theStudent) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "update student " + "set first_name=?, last_name=?, email=? "
                    + "where id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, theStudent.getFirstName());
            myStmt.setString(2, theStudent.getLastName());
            myStmt.setString(3, theStudent.getEmail());
            myStmt.setInt(4, theStudent.getId());

            myStmt.execute();
        }
        finally {
            close(myConn, myStmt, null);
        }
    }

    public void deleteStudent(Student theStudent) throws Exception {
        int studentId = theStudent.getId();

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try{
            myConn = dataSource.getConnection();
            String sql = "delete from student where id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, studentId);
            myStmt.execute();
        }
        finally {
            close(myConn, myStmt, null);
        }
    }

    public List<Student> searchStudent(String firstNameOrLastName) throws Exception {
        List<Student> students = new ArrayList<>();
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try{
            myConn = dataSource.getConnection();
            String sql = "select * from student where first_name=? " + "or last_name=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, firstNameOrLastName);
            myStmt.setString(2, firstNameOrLastName);
            myRs = myStmt.executeQuery();
            System.out.println(myRs);
            while (myRs.next()){
                int id = myRs.getInt("id");
                String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");
                System.out.println(myRs);
                Student tempStudent = new Student(id, firstName, lastName, email);
                students.add(tempStudent);
            }

            return students;
        }
        finally {
            close(myConn, myStmt, myRs);
        }
    }
}

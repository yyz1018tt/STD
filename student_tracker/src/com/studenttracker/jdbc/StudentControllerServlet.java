package com.studenttracker.jdbc;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentControllerServlet", urlPatterns = {"/students"})
public class StudentControllerServlet extends HttpServlet {

    private StudentDbUtil studentDbUtil;

    @Resource(name="jdbc/web_student_tracker")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            studentDbUtil = new StudentDbUtil(dataSource);
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String theCommand = request.getParameter("command");
            if(theCommand == null){
                theCommand = "LIST";
            }

            switch (theCommand){
                case "LIST":
                    listStudents(request, response);
                    break;

                case "ADD":
                    addStudent(request, response);
                    break;

                case "LOAD":
                    loadStudent(request, response);
                    break;

                case "UPDATE":
                    updateStudent(request, response);
                    break;

                case "DELETE":
                    deleteStudent(request, response);
                    break;

                case "SEARCH":
                    searchStudent(request, response);
                    break;

                default:
                    listStudents(request, response);
            }

            listStudents(request, response);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void searchStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String firstNameOrLastName = request.getParameter("search");
        List<Student> students = studentDbUtil.searchStudent(firstNameOrLastName);
        request.setAttribute("STUDENTS", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/find-student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String theStudentId = request.getParameter("studentId");
        Student theStudent = studentDbUtil.getStudent(theStudentId);
        studentDbUtil.deleteStudent(theStudent);
        listStudents(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("studentId"));
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String email = request.getParameter("email");

        Student theStudent = new Student(id, firstName, lastName, email);
        studentDbUtil.updateStudent(theStudent);
        listStudents(request, response);
    }


    private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //read student id from form
        String theStudentId = request.getParameter("studentId");

        //get student from db
        Student theStudent = studentDbUtil.getStudent(theStudentId);

        //place student in the request attribute
        request.setAttribute("THE_STUDENT", theStudent);
        //send to jsp page: update-student-form
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
          String firstName = request.getParameter("first-name");
          String lastName = request.getParameter("last-name");
          String email = request.getParameter("email");

          Student theStudent = new Student(firstName, lastName, email);
          studentDbUtil.addStudent(theStudent);
          listStudents(request, response);
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Student> students = studentDbUtil.getStudents();
        request.setAttribute("STUDENT_LIST", students);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
        dispatcher.forward(request, response);
    }
}

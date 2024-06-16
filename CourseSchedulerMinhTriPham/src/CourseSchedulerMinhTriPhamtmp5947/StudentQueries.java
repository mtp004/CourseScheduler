/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CourseSchedulerMinhTriPhamtmp5947;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author tripham
 */
public class StudentQueries {
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudents;
    private static PreparedStatement getStudent;
    private static ResultSet resultSet;
    private static PreparedStatement dropStudent;
    
    public static void addStudent(StudentEntry studentEntry){
        connection=DBConnection.getConnection();
        
        try{
            addStudent=connection.prepareStatement("insert into app.student (studentid, firstname, lastname) values (?,?,?)");
            addStudent.setString(1, studentEntry.GetStudentID());
            addStudent.setString(2, studentEntry.GetFirstName());
            addStudent.setString(3, studentEntry.GetLastName());
            addStudent.executeUpdate();
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<StudentEntry> getAllStudent(){
        connection=DBConnection.getConnection();
        ArrayList<StudentEntry> allStudent=new ArrayList();
        
        try{
            getAllStudents=connection.prepareStatement("select * from app.student");
            
            resultSet=getAllStudents.executeQuery();
            
            while(resultSet.next()){
                
                StudentEntry student=new StudentEntry(resultSet.getString("studentid"),resultSet.getString("firstname"),resultSet.getString("lastname"));
                
                allStudent.add(student);
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return allStudent;
    }
    
    public static void dropStudent(String studentID){
        connection=DBConnection.getConnection();
        try{
            dropStudent=connection.prepareStatement("delete from app.student where studentid=?");
            dropStudent.setString(1, studentID);
            dropStudent.executeUpdate();
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static StudentEntry getStudent(String studentID){
        connection=DBConnection.getConnection();
        String firstName="";
        String lastName="";
        try{
            getStudent=connection.prepareStatement("select * from app.student where studentid=?");
            getStudent.setString(1, studentID);
            resultSet=getStudent.executeQuery();
            resultSet.next();
            firstName=resultSet.getString("firstname");
            lastName=resultSet.getString("lastname");
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        StudentEntry student=new StudentEntry(studentID, firstName,lastName);
        return student;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CourseSchedulerMinhTriPhamtmp5947;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
/**
 *
 * @author tripham
 */
public class MultiTableQueries {
    private static Connection connection;
    private static PreparedStatement getAllCourseDescription;
    private static PreparedStatement getScheduledStudentsByClass;
    private static PreparedStatement getWaitlistedStudentByClass;
    private static ResultSet resultSet;
    
    public static ArrayList<ClassDescription> getAllCourseDescription(String semester){
        connection=DBConnection.getConnection();
        ArrayList<ClassDescription> classDescriptionList=new ArrayList();
        try{
            getAllCourseDescription=connection.prepareStatement("select app.class.coursecode, app.class.seats, app.course.description from app.class inner join app.course on (app.class.semester=app.course.semester and app.class.coursecode=app.course.coursecode) where app.class.semester=?");
            
            getAllCourseDescription.setString(1, semester);
            
            resultSet=getAllCourseDescription.executeQuery();
            
            while(resultSet.next()){
                ClassDescription classDescription=new ClassDescription(resultSet.getString("description"),resultSet.getString("coursecode"),resultSet.getInt("seats"));
                
                classDescriptionList.add(classDescription);
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return classDescriptionList;
    }
    
    public static ArrayList<StudentEntry> getScheduledStudentsByClass(String semester, String courseCode){
        connection=DBConnection.getConnection();
        ArrayList<StudentEntry> scheduledStudentByClass=new ArrayList();
        try{
            getScheduledStudentsByClass=connection.prepareStatement("select studentid from app.schedule where(semester=? and coursecode=? and status='s')");
            
            getScheduledStudentsByClass.setString(1,semester);
            
            getScheduledStudentsByClass.setString(2,courseCode);
            
            resultSet=getScheduledStudentsByClass.executeQuery();
            
            while(resultSet.next()){
                scheduledStudentByClass.add(StudentQueries.getStudent(resultSet.getString(1)));
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return scheduledStudentByClass;
    }
    
    public static ArrayList<StudentEntry> getWaitlistedStudentsByClass(String semester, String courseCode){
        connection=DBConnection.getConnection();
        ArrayList<StudentEntry> waitlistedStudentByClass=new ArrayList();
        try{
            getWaitlistedStudentByClass=connection.prepareStatement("select studentid from app.schedule where(semester=? and coursecode=? and status='w')");
            
            getWaitlistedStudentByClass.setString(1,semester);
            
            getWaitlistedStudentByClass.setString(2,courseCode);
            
            resultSet=getWaitlistedStudentByClass.executeQuery();
            
            while(resultSet.next()){
                waitlistedStudentByClass.add(StudentQueries.getStudent(resultSet.getString(1)));
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return waitlistedStudentByClass;
    }
}


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
public class CourseQueries {
    private static Connection connection;
    private static PreparedStatement addCourse;
    private static PreparedStatement getAllCourseCodes;
    private static ResultSet resultSet;
    
    //this method is the addCcourse method that takes in a CourseEntry object and input the data into the DB
    //declare it static so we dont have to have a CourseQueries object to call the addCourse method
    public static void addCourse(CourseEntry courseEntry, String semester){
        connection = DBConnection.getConnection();
        
        try{
            addCourse=connection.prepareStatement("insert into app.course (coursecode, description, semester) values (?,?,?)");
            addCourse.setString(1, courseEntry.GetCourseCode());
            addCourse.setString(2, courseEntry.GetCourseDescription());
            addCourse.setString(3, semester);
            addCourse.executeUpdate();
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    //this method returns a String ArrayList of all CourseDescription String variable from the COURSE table in the DB
    public static ArrayList<String> getAllCourseCodes(String semester){
        connection=DBConnection.getConnection();
        ArrayList<String> allCourseCode=new ArrayList();
        
        try{
            getAllCourseCodes=connection.prepareStatement("select coursecode from app.course where semester=? order by coursecode");
            
            getAllCourseCodes.setString(1,semester);
                    
            resultSet = getAllCourseCodes.executeQuery();
            
            while(resultSet.next()){
                allCourseCode.add(resultSet.getString(1));
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return allCourseCode;
    }
}

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
public class ClassQueries {
    private static Connection connection;
    private static PreparedStatement addClass;
    private static PreparedStatement getAllCourseCode;
    private static PreparedStatement dropClass;
    private static PreparedStatement getClassSeat;
    private static ResultSet resultSet;
    private static PreparedStatement updateSeats;
    
    public static void addClass(ClassEntry classEntry){
        connection=DBConnection.getConnection();
        
        try{
            addClass=connection.prepareStatement("insert into app.class (semester, coursecode, seats) values (?,?,?)");
            
            addClass.setString(1, classEntry.GetSemester());
            
            addClass.setString(2, classEntry.GetCourseCode());
            
            addClass.setString(3, Integer.toString(classEntry.GetSeats()));
            
            addClass.executeUpdate();
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getAllCourseCode(String semester){
        connection=DBConnection.getConnection();
        ArrayList<String> allCourseCode=new ArrayList();
        
        try{
            getAllCourseCode=connection.prepareStatement("select coursecode from app.class where semester=? order by coursecode");
            
            getAllCourseCode.setString(1,semester);
                    
            resultSet = getAllCourseCode.executeQuery();
            
            while(resultSet.next()){
                allCourseCode.add(resultSet.getString(1));
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return allCourseCode;
    }
    
    public static int getClassSeats(String semester, String courseCode){
        connection=DBConnection.getConnection();
        int seat=0;
        try{
            getClassSeat=connection.prepareStatement("select seats from app.class where (semester=? and coursecode=?)");
            
            getClassSeat.setString(1,semester);
            
            getClassSeat.setString(2,courseCode);
            
            resultSet=getClassSeat.executeQuery();
            resultSet.next();
            seat=resultSet.getInt(1);
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return seat;
    }
    
    public static void dropClass(String semester, String courseCode){
        connection=DBConnection.getConnection();
        try{
            dropClass=connection.prepareStatement("delete from app.class where(semester=? and coursecode=?)"); 
            
            dropClass.setString(1, semester);
            
            dropClass.setString(2, courseCode);
            
            dropClass.executeUpdate();
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }       
    }
    
    public static void updateSeats(String semester, String courseCode, int seat){
        connection=DBConnection.getConnection();
        try{
            updateSeats=connection.prepareStatement("update app.class set seats=seats+? where (semester=? and coursecode=?)");
            updateSeats.setInt(1, seat);
            updateSeats.setString(2,semester);
            updateSeats.setString(3,courseCode);
            updateSeats.executeUpdate();
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}

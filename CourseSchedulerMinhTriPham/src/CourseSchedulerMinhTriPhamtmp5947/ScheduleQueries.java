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
public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getWaitlistedStudentsByClass;
    private static PreparedStatement updateScheduleEntry;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement dropScheduleByCourse;
    private static ResultSet resultSet;
    private static PreparedStatement decreaseSeatBy1;
    
    public static void addScheduleEntry(ScheduleEntry scheduleEntry){
        connection = DBConnection.getConnection();
        try{
            addScheduleEntry=connection.prepareStatement("insert into app.schedule (semester, coursecode, studentid, status, timestamp) values (?,?,?,?,?)");
            addScheduleEntry.setString(1, scheduleEntry.GetSemester());
            addScheduleEntry.setString(2, scheduleEntry.GetCourseCode());
            addScheduleEntry.setString(3, scheduleEntry.GetStudentID());
            addScheduleEntry.setString(4, scheduleEntry.GetStatus());
            addScheduleEntry.setString(5, scheduleEntry.GetCurrentTimeStamp().toString());
            addScheduleEntry.executeUpdate();
            if(scheduleEntry.GetStatus().equals("s")){
                   ClassQueries.updateSeats(scheduleEntry.GetSemester(), scheduleEntry.GetCourseCode(), -1);
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> scheduleByStudent=new ArrayList();
        
        try{
            getScheduleByStudent=connection.prepareStatement("select * from app.schedule where (semester=? and studentid=?)");
            getScheduleByStudent.setString(1,semester);
            getScheduleByStudent.setString(2,studentID);
            resultSet=getScheduleByStudent.executeQuery();
            
            while(resultSet.next()){
                ScheduleEntry scheduleEntry= new ScheduleEntry(resultSet.getString("semester"),resultSet.getString("coursecode"),resultSet.getString("studentid"), resultSet.getString("status"), resultSet.getTimestamp("timestamp"));
                
                scheduleByStudent.add(scheduleEntry);
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return scheduleByStudent;
    }
    
    public static int getScheduledStudentCount(String currentSemester, String courseCode){
        connection = DBConnection.getConnection();
        int count=0;
        try{
            getScheduledStudentCount=connection.prepareStatement("select count(*) from app.schedule where status='s'");
            
            resultSet=getScheduleByStudent.executeQuery();
            
            count=resultSet.getInt(1);
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return count;
    }  
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByClass(String semester, String courseCode){
        connection=DBConnection.getConnection();
        ArrayList<ScheduleEntry> waitlistedByClass=new ArrayList();
        try{
            getWaitlistedStudentsByClass=connection.prepareStatement("select * from app.schedule where (semester=? and coursecode=? and status='w') order by timestamp asc");
            getWaitlistedStudentsByClass.setString(1,semester);
            getWaitlistedStudentsByClass.setString(2,courseCode);
            resultSet=getWaitlistedStudentsByClass.executeQuery();
            
            while(resultSet.next()){
                ScheduleEntry scheduleEntry= new ScheduleEntry(resultSet.getString("semester"),resultSet.getString("coursecode"),resultSet.getString("studentid"), resultSet.getString("status"), resultSet.getTimestamp("timestamp"));
                
                waitlistedByClass.add(scheduleEntry);
            }
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return waitlistedByClass;
    }
    
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode){
        connection=DBConnection.getConnection();
        try{
            dropStudentScheduleByCourse=connection.prepareStatement("delete from app.schedule where(semester=? and studentid=? and coursecode=?)");
            dropStudentScheduleByCourse.setString(1,semester);
            dropStudentScheduleByCourse.setString(2,studentID);
            dropStudentScheduleByCourse.setString(3,courseCode);
            dropStudentScheduleByCourse.executeUpdate();
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static void dropScheduleByCourse(String semester, String courseCode){
        connection=DBConnection.getConnection();
        try{
            dropScheduleByCourse=connection.prepareStatement("delete from app.schedule where(semester=? and coursecode=?)");
            dropScheduleByCourse.setString(1,semester);
            dropScheduleByCourse.setString(2,courseCode);
            dropScheduleByCourse.executeUpdate();
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static void updateScheduleEntry(ScheduleEntry entry){
        connection=DBConnection.getConnection();
        try{
            updateScheduleEntry=connection.prepareStatement("update app.schedule set status='s' where (semester=? and coursecode=? and studentid=?)");
            updateScheduleEntry.setString(1,entry.GetSemester());
            updateScheduleEntry.setString(2,entry.GetCourseCode());
            updateScheduleEntry.setString(3,entry.GetStudentID());
            updateScheduleEntry.executeUpdate();
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}

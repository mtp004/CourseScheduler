package CourseSchedulerMinhTriPhamtmp5947;



import java.util.Calendar;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tripham
 */
public class ScheduleEntry {
    private String semester;
    private String courseCode;
    private String studentID;
    private String status;
    private java.sql.Timestamp currentTimestamp;
    
    //contructor that takes in 4 String variables semester, courseCode, studentID, status from the JTextField from the GUI
    public ScheduleEntry(String semester, String courseCode, String studentID, String status, java.sql.Timestamp currentTimestamp){
        this.semester=semester;
        this.courseCode=courseCode;
        this.status=status;
        this.studentID=studentID;
        this.currentTimestamp=currentTimestamp;
    }
    
    //semester getter method
    public String GetSemester(){
        return semester;
    }
    
    //courseCode getter method
    public String GetCourseCode(){
        return courseCode;
    }
    
    //status getter method
    public String GetStatus(){
        return status;
    }
    
    //studentID getter method
    public String GetStudentID(){
        return studentID;
    }
    
    //currentTimestamp getter method
    public java.sql.Timestamp GetCurrentTimeStamp(){
        return currentTimestamp;
    }
  
}

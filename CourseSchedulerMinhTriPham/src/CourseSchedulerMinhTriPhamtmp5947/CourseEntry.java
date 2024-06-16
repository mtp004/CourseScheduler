package CourseSchedulerMinhTriPhamtmp5947;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tripham
 */
public class CourseEntry {
    private String courseCode;
    private String courseDescription;
    
    //this is the contructor that takes in 2 string variable courseCode and courseDescription from the JTextField in the GUI.
    public CourseEntry(String courseCode, String courseDescription){
        this.courseCode=courseCode;
        this.courseDescription=courseDescription;
    }
    
    // courseCode getter method
    public String GetCourseCode(){
        return courseCode;
    }
    
    //courseDescription getter method
    public String GetCourseDescription(){
        return courseDescription;
    }

}

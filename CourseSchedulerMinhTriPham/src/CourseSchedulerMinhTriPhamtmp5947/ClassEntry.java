package CourseSchedulerMinhTriPhamtmp5947;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tripham
 */
public class ClassEntry {
    private String semester;
    private String courseCode;
    private int seats;
    
    //this is the contructor that takes in 2 string variable semester and courseCode and int seats from the JTextField in the GUI.
    public ClassEntry(String semester, String courseCode, int seats){
        this.courseCode=courseCode;
        this.semester=semester;
        this.seats=seats;
    }
    
    //courseCode getter method
    public String GetCourseCode(){
        return courseCode;
    }
    
    //semester getter method
    public String GetSemester(){
        return semester;
    }
    
    //seats getter method
    public int GetSeats(){
        return seats;
    }
}

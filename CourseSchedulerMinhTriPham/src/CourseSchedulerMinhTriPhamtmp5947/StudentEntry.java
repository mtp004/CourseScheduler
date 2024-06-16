package CourseSchedulerMinhTriPhamtmp5947;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tripham
 */
public class StudentEntry {
    private String studentID;
    private String firstName;
    private String lastName;
    
    //this is the contructor that takes in 3 string variables studentID, firstName, lastName from the JTextField from the GUI
    public StudentEntry(String studentID, String firstName, String lastName){
        this.studentID=studentID;
        this.firstName=firstName;
        this.lastName=lastName;
    }
    
    //studentID getter method
    public String GetStudentID(){
        return studentID;
    }
    
    //firstName getter method
    public String GetFirstName(){
        return firstName;
    }
    
    //lastName getter method
    public String GetLastName(){
        return lastName;
    }
    
    @Override
    public String toString(){
        return firstName+" "+ lastName+", "+studentID;
    }
}

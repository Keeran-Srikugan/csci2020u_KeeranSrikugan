package sample;

public class StudentRecord {

    //Here are the private variables used in the class
    private String studentID;
    private float midterm;
    private float assignments;
    private float finalExam;
    private float finalMark;

    //Constructor
    public StudentRecord(String ID, float a, float m, float f){
        studentID = ID;
        midterm = m;
        assignments = a;
        finalExam = f;
    }

    //This function calculates the final mark with everything and returns it
    public float getFinalMark(){
        finalMark = (assignments * 0.20f) + (midterm * 0.30f) + (finalExam * 0.50f);
        return finalMark;
    }

    //This function takes the final mark calculated earlier and checks to see what grade the student has received.
    //Based on the grade, the corresponding letter is returned.
    public String getLetterGrade(){
        if(finalMark >= 80){
            return "A";
        }else if (finalMark >= 70){
            return "B";
        }else if (finalMark >= 60){
            return  "C";
        }else if (finalMark >= 50){
            return "D";
        }else {
            return "F";
        }
    }

    public String getStudentID(){
        return studentID;
    }
    public float getMidterm(){
        return midterm;
    }
    public float getAssignments(){
        return assignments;
    }
    public float getFinalExam(){
        return finalExam;
    }
}
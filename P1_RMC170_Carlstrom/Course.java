public class Course {
    // ID for Course and Name of course
    private String courseID, courseName;
    // max amount of people in a course
    private int capacity;

    // course creation method
    public Course(String IDOfCourse, String name, int size) {

        courseID = IDOfCourse;
        courseName = name;
        capacity = size;

    }

    // Blank Course Constructor
    public Course() {
        String[] courseIDList = { "CSDS 132", "CSDS 133", "CSDS 233", "CSDS 234", "CSDS 236", "CSDS 275", "CSDS 281",
                "CSDS 285", "CSDS 290", "CSDS 293", "CSDS 296", "CSDS 297" };
        String[] courseNameList = { "Introduction to Programming in Java",
                "Introduction to Data Science and Engineering for Majors", "Introduction to Data Structures",
                "Structured and Unstructured Data", "Introduction to C/C++ Programming", "Fundamentals of Robotics",
                "Logic Design and Computer Organization", "Linux Tools and Scripting",
                "Introduction to Computer Game Design and Implementation", "Software Craftsmanship",
                "Independent Projects", "Special Topics" };
        int classSize, courseIDnumber;

        classSize = (int) (Math.random() * 100);
        courseIDnumber = (int) (Math.random() * courseIDList.length);

        courseID = courseIDList[courseIDnumber];
        courseName = courseNameList[courseIDnumber];
        capacity = classSize;
    }

    // Update the ID of a course
    void setID(String ID) {
        courseID = ID;
    }

    // Update the capacity of a course
    void setCapacity(int size) {
        capacity = size;
    }

    // Update the name of a course
    void setName(String name) {
        courseName = name;
    }

    // Course turned into string
    String courseToString() {
        return new String("CourseID: " + courseID + ", course name: " + courseName + ", capacity: " + capacity);
    }
}
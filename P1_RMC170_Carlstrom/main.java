public class main {

    public static void main(String[] args) {

        CourseList CWRUCourses = new CourseList();
        System.out.println(CWRUCourses.courseListToString());

        
        Course testCourse1 = new Course("1234", "Cool Beanz", 327);
        CWRUCourses.addCourse(2, testCourse1);
    }

}

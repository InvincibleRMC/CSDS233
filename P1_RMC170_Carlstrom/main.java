public class main {

    public static void main(String[] args) {

        CourseList CWRUCourses = new CourseList();
        //System.out.println(CWRUCourses.courseListToString());

        
        Course testCourse1 = new Course("EZ 1234", "Cool Beanz", 327);
        CWRUCourses.addCourse(2, testCourse1);
        CWRUCourses.removeCourse(3);
        System.out.println(CWRUCourses.SearchCourseID("EZ 1234"));
        System.out.println(CWRUCourses.SearchCourseName("Introduction to Programming in Java"));
    }

}

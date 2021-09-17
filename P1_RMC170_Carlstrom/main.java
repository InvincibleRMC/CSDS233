public class main {

    public static void main(String[] args) {

        //Initialization of empty CoursList object
        CourseList CWRUCourses = new CourseList();
        System.out.println(CWRUCourses.courseListToString());

        //Test Code feel free to delete or modify
        Course testCourse1 = new Course("EZ 1234", "Cool Beanz", 327);
        CWRUCourses.addCourse(2, testCourse1);
        CWRUCourses.removeCourse(3);
        System.out.println(CWRUCourses.SearchCourseID("EZ 1234"));
        System.out.println(CWRUCourses.SearchCourseName("Introduction to Programming in Java"));
        CWRUCourses.changeCapacity("EZ 1234", 192);
    }

}

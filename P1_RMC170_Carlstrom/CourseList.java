public class CourseList {

    private Course[] listOfCourses;
    private int size = 0;
    private int length = 0;

    public CourseList(int length) {

        listOfCourses = new Course[length];
        this.length = length;
    }

    public CourseList() {

        length = 10;
        Course newCourse;
        listOfCourses = new Course[length];

        for (int i = 0; i < (length >> 1); i++) {
            newCourse = new Course();
            listOfCourses[i] = newCourse;
        }

    }

    public int getSize() {
        return size;
    }

    public int getLength() {
        return length;
    }

    String courseListToString() {

        if (getSize() == 0) {
            return "Course List Empty";
        }

        String courseListToString = "";

        for (int i = 0; i < getSize(); i++) {
            courseListToString = courseListToString + i + ". " + listOfCourses[i].courseToString() + "\n";
        }

        return courseListToString;
    }

    void shiftCourse(int start, int end) {
        for (int i = end - 1; i > start; i--) {
            listOfCourses[i + 1] = listOfCourses[i];
        }
    }

    void addCourse(int i, Course course) {

        System.out.println(i + " " + getSize());
        int j = 0;
        if (i < 0) {

            System.out.println("Can't insert a negative index. Will insert before 0th index.");
            j = 0;

        } else if (i >= getSize()) {

            System.out.println("Can't insert to an index bigger than the size. Will insert before the last index.");

            j = getSize();
        } else {
            j = i - 1;
        }

        System.out.println("Course added before index " + j + ".");
        System.out.println("Course: " + course.courseToString());
        System.out.println(courseListToString());

        size++;
        shiftCourse(j, getSize());
        listOfCourses[j] = course;
        System.out.println("List after operation");
        System.out.println(courseListToString());
    }
    /*
     * boolean removeCourse(int i){ size--; if(i < 0){
     * System.out.println("Failed to remove element. You gave a negative index");
     * return false; } else if(i) }
     * 
     */

    Course getCourseWithIndex(int i) {

        if (i < 0) {
            System.out.println("Negative index don't make sense. Will return null.");
            return null;
        } else if (i > getSize()) {
            System.out.println("indexes larger than the size don't make sense. Will return null.");
            return null;
        }
        return listOfCourses[i];
    }
}

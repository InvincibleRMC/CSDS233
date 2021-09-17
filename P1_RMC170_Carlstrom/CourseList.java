public class CourseList {

    private Course[] listOfCourses;
    private int size = 0;
    private int length = 0;

    public CourseList(int length) {

        listOfCourses = new Course[length];
        this.length = length;
        size = 0;
    }

    public CourseList() {
        length = 10;
        Course newCourse;
        listOfCourses = new Course[length];
        for (int i = 0; i < (length >> 1); i++) {
            newCourse = new Course();
            listOfCourses[i] = newCourse;
            size++;
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

    void shiftCourseLeft(int start, int end) {
        for (int i = start + 1; i < end + 1; i++) {
            listOfCourses[i - 1] = listOfCourses[i];
        }
    }

    void shiftCourseRight(int start, int end) {
        for (int i = end - 1; i > start - 1; i--) {
            listOfCourses[i + 1] = listOfCourses[i];
        }
    }

    void addCourse(int i, Course course) {

        int j = 0;
        if (i < 0) {

            System.out.println("Can't insert a negative index. Will insert before 0th index.");
            j = 0;

        } else if (i >= getSize()) {

            System.out.println("Can't insert to an index bigger than the size. Will insert before the last index.");

            j = getSize();
        } else {
            j = i;
        }

        System.out.println("Course added before index " + j + ".");
        System.out.println("Course: " + course.courseToString());
        System.out.println(courseListToString());

        size++;
        shiftCourseRight(j, getSize());
        listOfCourses[j] = course;
        System.out.println("List after operation");
        System.out.println(courseListToString());
    }

    boolean removeCourse(int i) {

        if (i < 0) {
            System.out.println("Failed to remove element. You gave a negative index");
            return false;
        } else if (i > size) {
            System.out.println("Failed to remove element. You gave an index larger than amount of elements");
            return false;
        }

        System.out.println("Course removed at index " + i + ".");
        System.out.println(courseListToString());

        size--;
        shiftCourseLeft(i, getSize());
        System.out.println("List after operation");
        System.out.println(courseListToString());

        return true;
    }

    boolean changeCapacity(String courseID, int capacity){

        int index = SearchCourseID(courseID);
        if (index == -1){
            return false;
        }
        
        listOfCourses[index].setCapacity(capacity);

        return true;
    }

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

    int SearchCourseID(String courseID) {
        for (int i = 0; i < getSize(); i++) {
            if (0 == courseID.compareTo(getCourseWithIndex(i).getID())) {
                return i;
            }
        }
        return -1;
    }

    int SearchCourseName(String courseName) {
        for (int i = 0; i < getSize(); i++) {
            if (0 == courseName.compareTo(getCourseWithIndex(i).getName())) {
                return i;
            }
        }
        return -1;
    }
}

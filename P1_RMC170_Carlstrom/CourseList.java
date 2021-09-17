public class CourseList {

    // Initialization of variables
    private Course[] listOfCourses;
    private int size = 0;
    private int length = 0;

    // CourseList Constructor given a length Note all Course are undefined here
    public CourseList(int length) {
        listOfCourses = new Course[length];
        this.length = length;
        size = 0;
    }

    // CourseList Constructor given no inputs. Creates with a length of 10.
    // Randomizes the first half of the CourseList with random courses.
    public CourseList() {
        length = 10;
        Course newCourse;
        listOfCourses = new Course[length];
        // Adds Courses for half the length of the Course Array
        for (int i = 0; i < (length >> 1); i++) {
            newCourse = new Course();
            listOfCourses[i] = newCourse;
            size++;
        }
    }

    // gets the size of CourseList
    public int getSize() {
        return size;
    }

    // gets the length of CourseList
    public int getLength() {
        return length;
    }

    // ToString method for courseList
    String courseListToString() {

        // If the courseList is empty return no information about courses
        if (getSize() == 0) {
            return "Course List Empty";
        }

        // For each Course add the information from the courseToString together.
        String courseListToString = "";
        for (int i = 0; i < getSize(); i++) {
            courseListToString = courseListToString + i + ". " + listOfCourses[i].courseToString() + "\n";
        }

        return courseListToString;
    }

    // Shifts Courses to the Left removing gaps
    void shiftCourseLeft(int start, int end) {
        for (int i = start + 1; i < end + 1; i++) {
            listOfCourses[i - 1] = listOfCourses[i];
        }
    }

    // Shifts Courses to the Right creating gaps
    void shiftCourseRight(int start, int end) {
        for (int i = end - 1; i > start - 1; i--) {
            listOfCourses[i + 1] = listOfCourses[i];
        }
    }

    // Adds Course before the ith index in the CourseList
    void addCourse(int i, Course course) {

        // Dealing with cases where i is to small or too large
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

        // Before Status
        System.out.println("Course added before index " + j + ".");
        System.out.println("Course: " + course.courseToString());
        System.out.println(courseListToString());

        // Updates Course Positions
        size++;
        shiftCourseRight(j, getSize());
        listOfCourses[j] = course;

        // After Status
        System.out.println("List after operation");
        System.out.println(courseListToString());
    }

    // Removes the Course at the index of i assuming it exists.
    // If it does return true if not return false.
    boolean removeCourse(int i) {

        // Handling invalid cases by returning false
        if (i < 0) {
            System.out.println("Failed to remove element. You gave a negative index");
            return false;
        } else if (i > size) {
            System.out.println("Failed to remove element. You gave an index larger than amount of elements");
            return false;
        }
        // Before Status
        System.out.println("Course removed at index " + i + ".");
        System.out.println(courseListToString());

        // Updates Course Positions
        size--;
        shiftCourseLeft(i, getSize());

        // After Status
        System.out.println("List after operation");
        System.out.println(courseListToString());

        // Succesfully returns True
        return true;
    }

    boolean changeCapacity(String courseID, int capacity) {

        // Finds location of index if it exists
        int index = SearchCourseID(courseID);

        // handels case where the index does not exists by returning false
        if (index == -1) {
            return false;
        }

        // Before Status
        System.out.println("Course apacity changed for course " + courseID + ".");
        System.out.println(courseListToString());

        // Updates Course capacity
        listOfCourses[index].setCapacity(capacity);

        // After Status
        System.out.println("List after operation");
        System.out.println(courseListToString());

        // Succesfully returns True
        return true;
    }

    // Retrieves the Course given an index
    Course getCourseWithIndex(int i) {

        // handels cases where the index does not exist
        if (i < 0) {
            System.out.println("Negative index don't make sense. Will return null.");
            return null;
        } else if (i > getSize()) {
            System.out.println("indexes larger than the size don't make sense. Will return null.");
            return null;
        }
        // returns the course from the given index
        return listOfCourses[i];
    }

    // Returns the index of a Course given its ID
    int SearchCourseID(String courseID) {
        // looks throught CourseList for a match if found returns the index of the math
        for (int i = 0; i < getSize(); i++) {
            if (0 == courseID.compareTo(getCourseWithIndex(i).getID())) {
                return i;
            }
        }
        // If it does not find a match return -1
        return -1;
    }

    // Returns the index of a Course given its name
    int SearchCourseName(String courseName) {
        // looks throught CourseList for a match if found returns the index of the math
        for (int i = 0; i < getSize(); i++) {
            if (0 == courseName.compareTo(getCourseWithIndex(i).getName())) {
                return i;
            }
        }
        // If it does not find a match return -1
        return -1;
    }
}

package Random;
public class phoneBook{
    private int lengthOfPhoneBook;
    private int lastFullSpace = 0;
    private String[] phoneBookData;

public phoneBook(){
    lengthOfPhoneBook = 10;
    phoneBookData = new String[lengthOfPhoneBook];
    lastFullSpace = 3;
    phoneBookData[0] = "lel";
    phoneBookData[1] = "hi";
    phoneBookData[2] = "yeehaw";
}


public String getData(int position){

    return phoneBookData[position];
}



public void addData(String phoneName){

phoneBookData[lastFullSpace] = phoneName;

lastFullSpace++;
}

}
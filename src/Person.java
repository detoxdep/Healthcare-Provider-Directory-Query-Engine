public class Person {
    private String firstName;
    private char middleInitial;
    private String lastName;
    private int age;
    private char sex;
    private String primaryAddress;

public Person(String firstName, char middleInitial, String lastName, int age, char sex, String primaryAddress){
    this.firstName = firstName;
    this.middleInitial = middleInitial;
    this.lastName = lastName;
    this.age = age;
    this.sex = sex;
    this.primaryAddress = primaryAddress;

}
public String getFirstName() {
    return firstName;
}
public void setFirstName(String firstName) {
    this.firstName = firstName;
}
public char getMiddleInitial() {
    return middleInitial;
}
public void setMiddleInitial(char middleInitial) {
    this.middleInitial = middleInitial;
}
public String getLastName() {
    return lastName;
}
public void setLastName(String lastName) {
    this.lastName = lastName;
}
public int getAge() {
    return age;
}
public void setAge(int age) {
    this.age = age;
}
public char getSex() {
    return sex;
}
public void setSex(char sex) {
    this.sex = sex;
}


public String getPrimaryAddress() {
    return primaryAddress;
}
public void setPrimaryAddress(String primaryAddress) {
    this.primaryAddress = primaryAddress;
}
public int getZip(String address){
    String zipToken = address.replaceAll("\"", "").trim();   
return Integer.parseInt(zipToken.replaceAll(".*?(\\d{5}).*$", "$1"));  
    }



}


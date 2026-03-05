package src;
public class Patient extends Person {
    String insurance;
  

    public Patient(String firstName, char middleInitial, String lastName,
        int age, char sex, String insurance, String primaryAddress){
       super(firstName, middleInitial, lastName, age, sex, primaryAddress);
       this.insurance = insurance;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

  
    
}

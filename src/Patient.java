public class Patient extends Person {
    String insurance;
    String password;

    public Patient(String firstName, char middleInitial, String lastName,
        int age, char sex, String insurance, String primaryAddress, String password){
       super(firstName, middleInitial, lastName, age, sex, primaryAddress);
       this.insurance = insurance;
       this.password = password;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        // Encrypt user password in iteration 2 
    }

  
    
}

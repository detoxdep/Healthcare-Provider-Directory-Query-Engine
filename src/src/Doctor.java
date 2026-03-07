package src.src;
import java.util.Date;

public class Doctor extends Person {
    String NPI;
    Date enumerationDate;
    String[] languagesSpoke;
    String[] specialization;
    String secondaryAddress;
    String[] acceptedInsurance;
    boolean newPatients;
    String URL;
    String primaryPhone;
    String secondaryPhone;
    float reviewAvg;


    public Doctor(String firstName, char middleInitial, String lastName, int age, char sex, String NPI, 
        Date enumerationDate, String[] languagesSpoke, String[] specialization, String primaryAddress, 
        String secondaryAddress, String[] acceptedInsurance, boolean newPatients, String URL, 
        String primaryPhone, String secondaryPhone, float reviewAvg){
        super(firstName, middleInitial, lastName, age, sex, primaryAddress);
        this.NPI = NPI;
        this.enumerationDate = enumerationDate;
        this.languagesSpoke = languagesSpoke;
        this.specialization = specialization;
        this.secondaryAddress = secondaryAddress;
        this.acceptedInsurance = acceptedInsurance;
        this.newPatients = newPatients;
        this.URL = URL;
        this.primaryPhone = primaryPhone;
        this.secondaryPhone = secondaryPhone;
        this.reviewAvg = reviewAvg;     
       
    }


    public String getNPI() {
        return NPI;
    }


    public void setNPI(String nPI) {
        NPI = nPI;
    }


    public Date getEnumerationDate() {
        return enumerationDate;
    }


    public void setEnumerationDate(Date enumerationDate) {
        this.enumerationDate = enumerationDate;
    }


    public String[] getLanguagesSpoke() {
        return languagesSpoke;
    }


    public void setLanguagesSpoke(String[] languagesSpoke) {
        this.languagesSpoke = languagesSpoke;
    }


    public String[] getSpecialization() {
        return specialization;
    }


    public void setSpecialization(String[] specialization) {
        this.specialization = specialization;
    }


    public String getSecondaryAddress() {
        return secondaryAddress;
    }


    public void setSecondaryAddress(String secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }


    public String[] getAcceptedInsurance() {
        return acceptedInsurance;
    }


    public void setAcceptedInsurance(String[] acceptedInsurance) {
        this.acceptedInsurance = acceptedInsurance;
    }


    public boolean isNewPatients() {
        return newPatients;
    }


    public void setNewPatients(boolean newPatients) {
        this.newPatients = newPatients;
    }


    public String getURL() {
        return URL;
    }


    public void setURL(String uRL) {
        URL = uRL;
    }


    public String getPrimaryPhone() {
        return primaryPhone;
    }


    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }


    public String getSecondaryPhone() {
        return secondaryPhone;
    }


    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }


    public float getReviewAvg() {
        return reviewAvg;
    }


    public void setReviewAvg(float reviewAvg) {
        this.reviewAvg = reviewAvg;
    }
    
    @Override
    public String toString() {
    return "Doctor{" +
            "firstName='" + getFirstName() + '\'' +
            ", middleInitial=" + getMiddleInitial() +
            ", lastName='" + getLastName() + '\'' +
            ", age=" + getAge() +
            ", sex=" + getSex() +
            ", NPI='" + NPI + '\'' +
            ", enumerationDate=" + enumerationDate +
            ", languagesSpoke=" + String.join(";", languagesSpoke) +
            ", specialization=" + String.join(";", specialization) +
            ", primaryAddress='" + getPrimaryAddress() + '\'' +
            ", secondaryAddress='" + secondaryAddress + '\'' +
            ", acceptedInsurance=" + String.join(";", acceptedInsurance) +
            ", newPatients=" + newPatients +
            ", URL='" + URL + '\'' +
            ", primaryPhone='" + primaryPhone + '\'' +
            ", secondaryPhone='" + secondaryPhone + '\'' +
            ", reviewAvg=" + reviewAvg +
            '}';
}
    
   
}

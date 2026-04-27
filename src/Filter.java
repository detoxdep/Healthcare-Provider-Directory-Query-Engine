import java.util.List;

public class Filter {
    private Boolean acceptsNewPatients;
    public Boolean getAcceptsNewPatients() {
        return acceptsNewPatients;
    }


    public void setAcceptsNewPatients(Boolean acceptsNewPatients) {
        this.acceptsNewPatients = acceptsNewPatients;
    }


    public String getInsurance() {
        return insurance;
    }


    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }


    public List<String> getLanguages() {
        return languages;
    }


    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }


    public List<String> getSpecializations() {
        return specializations;
    }


    public void setSpecializations(List<String> specializations) {
        this.specializations = specializations;
    }


    public Float getMinReviewAvg() {
        return minReviewAvg;
    }


    public void setMinReviewAvg(Float minReviewAvg) {
        this.minReviewAvg = minReviewAvg;
    }


    private String insurance;
    private List<String> languages;
    private List<String> specializations;
    private Float minReviewAvg;

    
    public boolean applyFilters(Doctor doctor, int distance, Integer maxDistance) {

        if (acceptsNewPatients != null && doctor.isNewPatients() != acceptsNewPatients) {
            return false;
        }

        if (insurance != null) {
            boolean match = false;
            for (String accepted : doctor.getAcceptedInsurance()) {
                if (accepted.equalsIgnoreCase(insurance)) {
                    match = true;
                    break;
                }
            }
            if (!match) return false;
        }

       if (languages != null && !languages.isEmpty()) {
    boolean match = false;

    for (String docLang : doctor.getLanguagesSpoke()) {
        for (String filterLang : languages) {
            if (docLang.equalsIgnoreCase(filterLang)) {
                match = true;
                break;
            }
        }
        if (match) break;
    }

    if (!match) return false;
}

        if (maxDistance != null && distance > maxDistance) {
            return false;
        }
if (specializations != null && !specializations.isEmpty()) {
    boolean match = false;

    for (String docSpec : doctor.getSpecialization()) {
        for (String filterSpec : specializations) {
            if (docSpec.equalsIgnoreCase(filterSpec)) {
                match = true;
                break;
            }
        }
        if (match) break;
    }

    if (!match) return false;
}

        if (minReviewAvg != null && doctor.getReviewAvg() < minReviewAvg) {
            return false;
        }

        return true;
    }
    
   
}
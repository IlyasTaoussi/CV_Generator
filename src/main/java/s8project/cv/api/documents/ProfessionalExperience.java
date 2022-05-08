package s8project.cv.api.documents;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("professional_experience")
public class ProfessionalExperience {

    @Id
    @Field(value="expId")
    private int expId;

    @Field(value="position")
    private String position;

    @Field(value="company_name")
    private String company_name;

    @Field(value="localisation")
    private String localisation;

    @Field(value="start_date")
    private Date startDate;

    @Field(value="end_date")
    private Date endDate;

    @Field(value="description")
    private String description;

    @Field(value="technos")
    private List<String> technos;

    public int getId() {
        return expId;
    }

    public void setId(int expId) {
        this.expId = expId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTechnos() {
        return technos;
    }

    public void setTechnos(List<String> technos) {
        this.technos = technos;
    }

    public static void updateProfExp(List<ProfessionalExperience> profExps, ProfessionalExperience newProfExp){
        for(ProfessionalExperience profExp: profExps){
            if(profExp.getId() == newProfExp.getId()) {
                profExp.setDescription(newProfExp.getDescription());
                profExp.setTechnos(newProfExp.getTechnos());
                profExp.setLocalisation(newProfExp.getLocalisation());
                profExp.setCompany_name(newProfExp.getCompany_name());
                profExp.setPosition(newProfExp.getPosition());
                profExp.setStartDate(newProfExp.getStartDate());
                profExp.setEndDate(newProfExp.getEndDate());
                break;
            }
        }
    }
}

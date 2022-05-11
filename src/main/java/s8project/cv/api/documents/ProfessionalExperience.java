package s8project.cv.api.documents;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.ws.rs.core.Response;

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
    private String startDate;

    @Field(value="end_date")
    private String endDate;

    @Field(value="description")
    private String description;

    @Field(value="technos")
    private String technos;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnos() {
        return technos;
    }

    public void setTechnos(String technos) {
        this.technos = technos;
    }
}

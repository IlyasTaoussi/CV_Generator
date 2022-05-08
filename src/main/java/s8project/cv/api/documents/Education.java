package s8project.cv.api.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.ws.rs.core.Response;
import java.util.List;

@Document("education")
public class Education {

    @Id
    @Field(value="eduId")
    private int eduId;

    @Field(value="degree")
    private String degree;

    @Field(value="field")
    private String field;

    @Field(value="school")
    private String school;

    @Field(value="location")
    private String location;

    @Field(value="start_date")
    private String startDate;

    @Field(value="end_date")
    private String endDate;

    public Education(String degree, String field, String school, String location, String startDate, String endDate) {
        this.degree = degree;
        this.field = field;
        this.school = school;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return eduId;
    }

    public void setId(int eduId) {
        this.eduId = eduId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public static Education getEdu(List<Education> educations, int eduId){
        for(Education education: educations){
            if(education.getId() == eduId) return education;
        }
        return null;
    }

    public static int updateEdu(List<Education> educations, Education newEdu){
        Education education = getEdu(educations, newEdu.getId());
        if(education == null) return Response.Status.NOT_FOUND.getStatusCode();

        education.setDegree(newEdu.getDegree());
        education.setField(newEdu.getField());
        education.setSchool(newEdu.getSchool());
        education.setLocation(newEdu.getLocation());
        education.setStartDate(newEdu.getStartDate());
        education.setEndDate(newEdu.getEndDate());
        return Response.Status.OK.getStatusCode();
    }
}

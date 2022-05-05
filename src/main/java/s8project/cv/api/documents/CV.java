package s8project.cv.api.documents;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document("cv")
public class CV {
    @Field(value="contact")
    private Contact contact;

    @Field(value="profile")
    private String profile;

    @Field(value="professional_experience")
    private List<ProfessionalExperience> professionalExperience;

    @Field(value="education")
    private List<Education> education;

    @Field(value="skill")
    private List<Skill> skill;

    @Field(value="certification")
    private List<Certification> certification;

    @Field(value="language")
    private List<Language> language;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getField() {
        return profile;
    }

    public void setField(String profile) {
        this.profile = profile;
    }

    public List<ProfessionalExperience> getProfessionalExperience() {
        return professionalExperience;
    }

    public void setProfessionalExperience(List<ProfessionalExperience> professionalExperience) {
        this.professionalExperience = professionalExperience;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public List<Skill> getSkill() {
        return skill;
    }

    public void setSkill(List<Skill> skill) {
        this.skill = skill;
    }

    public List<Certification> getCertification() {
        return certification;
    }

    public void setCertification(List<Certification> certification) {
        this.certification = certification;
    }

    public List<Language> getLanguage() {
        return language;
    }

    public void setLanguage(List<Language> language) {
        this.language = language;
    }
}

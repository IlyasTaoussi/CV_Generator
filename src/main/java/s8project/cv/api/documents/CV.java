package s8project.cv.api.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Document("cv")
public class CV {

    @Id
    @Field(value="cvId")
    private int cvId;

    @Field(value="contact")
    private Contact contact;

    @Field(value="summary")
    private String summary;

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

    public CV(){
        contact = new Contact();
        professionalExperience = new ArrayList<>();
        education = new ArrayList<>();
        skill = new ArrayList<>();
        certification = new ArrayList<>();
        language = new ArrayList<>();
    }

    public int getId() {
        return cvId;
    }

    public void setId(int cvId) {
        this.cvId = cvId;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String profile) {
        this.summary = profile;
    }

    public List<ProfessionalExperience> getProfessionalExperience() {
        return professionalExperience;
    }

    public ProfessionalExperience getProfessionalExperience(int profExpId) {
        for(ProfessionalExperience profExp : professionalExperience){
            if(profExp.getId() == profExpId) return profExp;
        }
        return null;
    }

    public void setProfessionalExperience(List<ProfessionalExperience> professionalExperience) {
        this.professionalExperience = professionalExperience;
    }

    public int updateProfExp(ProfessionalExperience newProfExp){
        ProfessionalExperience profExp = getProfessionalExperience(newProfExp.getId());
        if(profExp == null) return Response.Status.NOT_FOUND.getStatusCode();
        profExp.setDescription(newProfExp.getDescription());
        profExp.setTechnos(newProfExp.getTechnos());
        profExp.setLocalisation(newProfExp.getLocalisation());
        profExp.setCompany_name(newProfExp.getCompany_name());
        profExp.setPosition(newProfExp.getPosition());
        profExp.setStartDate(newProfExp.getStartDate());
        profExp.setEndDate(newProfExp.getEndDate());
        return Response.Status.OK.getStatusCode();

    }

    public List<Education> getEducation() {
        return education;
    }

    public Education getEducation(int eduId) {
        for(Education education: this.education){
            if(education.getId() == eduId) return education;
        }
        return null;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }


    public int updateEdu(Education newEdu){
        Education education = getEducation(newEdu.getId());
        if(education == null) return Response.Status.NOT_FOUND.getStatusCode();

        education.setDegree(newEdu.getDegree());
        education.setField(newEdu.getField());
        education.setSchool(newEdu.getSchool());
        education.setLocation(newEdu.getLocation());
        education.setStartDate(newEdu.getStartDate());
        education.setEndDate(newEdu.getEndDate());
        return Response.Status.OK.getStatusCode();
    }

    public List<Skill> getSkill() {
        return skill;
    }

    public Skill getSkill(int skillId) {
        for(Skill skill : this.skill){
            if(skill.getId() == skillId) return skill;
        }
        return null;
    }

    public void setSkill(List<Skill> skill) {
        this.skill = skill;
    }

    public int updateSkill(Skill newSkill){
        Skill skill = getSkill(newSkill.getId());
        if(skill == null) return Response.Status.NOT_FOUND.getStatusCode();

        skill.setName(newSkill.getName());
        skill.setLevel(newSkill.getLevel());
        return Response.Status.OK.getStatusCode();
    }

    public List<Certification> getCertification() {
        return certification;
    }

    public Certification getCertification(int certId) {
        for(Certification cert: certification){
            if(cert.getId() == certId) return cert;
        }
        return null;
    }

    public int updateCert(Certification newCert){
        Certification cert = getCertification(newCert.getId());
        if(cert == null) return Response.Status.NOT_FOUND.getStatusCode();

        cert.setName(newCert.getName());
        cert.setDescription(newCert.getDescription());
        cert.setStartDate(newCert.getStartDate());
        cert.setEndDate(newCert.getEndDate());
        return Response.Status.OK.getStatusCode();

    }

    public void setCertification(List<Certification> certification) {
        this.certification = certification;
    }

    public List<Language> getLanguage() {
        return language;
    }

    public Language getLanguage(int langId) {
        for(Language lang: language){
            if(lang.getId() == langId) return lang;
        }
        return null;
    }

    public void setLanguage(List<Language> language) {
        this.language = language;
    }

    public int updateLang(Language newLang){
        Language lang = getLanguage(newLang.getId());
        if(lang == null) return Response.Status.NOT_FOUND.getStatusCode();

        lang.setName(newLang.getName());
        lang.setLevel(newLang.getLevel());
        return Response.Status.OK.getStatusCode();
    }
}

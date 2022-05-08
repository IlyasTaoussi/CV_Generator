package s8project.cv.api.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import s8project.cv.api.documents.*;
import s8project.cv.api.utilities.Utilities;


import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.List;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public User findByUserId(int userId){
        Query query = new Query(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public int getMaxUserId() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "userId"));
        query.limit(1);
        User user = mongoTemplate.findOne(query, User.class);
        if (user == null) {
            return 0;
        }
        return user.getUserId();
    }

    @Override
    public Certification getCert(int userId, int certId){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        return Certification.getCert(user.getCV().getCertification(), certId);
    }

    @Override
    public Contact getContact(int userId){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        return user.getCV().getContact();
    }

    @Override
    public Education getEducation(int userId, int eduId){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        return Education.getEdu(user.getCV().getEducation(), eduId);
    }

    @Override
    public ProfessionalExperience getProfExp(int userId, int profExpId){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        return ProfessionalExperience.getProfExp(user.getCV().getProfessionalExperience(), profExpId);
    }

    @Override
    public Language getLang(int userId, int langId){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        return Language.getLang(user.getCV().getLanguage(), langId);
    }

    @Override
    public Skill getSkill(int userId, int skillId){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        return Skill.getSkill(user.getCV().getSkill(), skillId);
    }

    @Override
    public Certification insertCert(int userId, Certification certification){
        int newId = 0;

        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        CV cv = user.getCV();

        if(cv == null) {
            cv = new CV();
            newId = 1;
        }
        else{
            List<Certification> certs = (List<Certification>) Utilities.copy(cv.getCertification());
            if(certs.size() != 0){
                certs.sort(Comparator.comparing(Certification::getId));
                newId = certs.get(certs.size() - 1).getId() + 1;
            }
            else{
                newId = 1;
            }
        }

        certification.setId(newId);
        cv.getCertification().add(certification);

        Update update = new Update();
        update.set("cv", cv);
        mongoTemplate.updateFirst(query, update, User.class);
        return certification;
    }

    @Override
    public Contact insertContact(int userId, Contact contact){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        CV cv = user.getCV();

        if(cv == null) cv = new CV();
        cv.setContact(contact);

        Update update = new Update();
        update.set("cv", cv);
        mongoTemplate.updateFirst(query, update, User.class);
        return contact;
    }

    @Override
    public Education insertEducation(int userId, Education education){
        int newId = 0;

        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        CV cv = user.getCV();

        if(cv == null) {
            cv = new CV();
            newId = 1;
        }
        else{
            List<Education> educations = (List<Education>) Utilities.copy(cv.getEducation());
            if(educations.size() != 0){
                educations.sort(Comparator.comparing(Education::getId));
                newId = educations.get(educations.size() - 1).getId() + 1;
            }
            else{
                newId = 1;
            }
        }

        education.setId(newId);
        cv.getEducation().add(education);

        Update update = new Update();
        update.set("cv", cv);
        mongoTemplate.updateFirst(query, update, User.class);
        return education;
    }

    @Override
    public Language insertLang(int userId, Language lang){
        int newId = 0;

        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        CV cv = user.getCV();

        if(cv == null) {
            cv = new CV();
            newId = 1;
        }
        else{
            List<Language> langs = (List<Language>) Utilities.copy(cv.getLanguage());
            if(langs.size() != 0){
                langs.sort(Comparator.comparing(Language::getId));
                newId = langs.get(langs.size() - 1).getId() + 1;
            }
            else{
                newId = 1;
            }

        }

        lang.setId(newId);
        cv.getLanguage().add(lang);

        Update update = new Update();
        update.set("cv", cv);
        mongoTemplate.updateFirst(query, update, User.class);
        return lang;
    }

    @Override
    public Skill insertSkill(int userId, Skill skill){
        int newId = 0;

        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        CV cv = user.getCV();

        if(cv == null) {
            cv = new CV();
            newId = 1;
        }
        else{
            List<Skill> skills = (List<Skill>) Utilities.copy(cv.getSkill());
            if(skills.size() != 0){
                skills.sort(Comparator.comparing(Skill::getId));
                newId = skills.get(skills.size() - 1).getId() + 1;
            }
            else{
                newId = 1;
            }
        }

        skill.setId(newId);
        cv.getSkill().add(skill);

        Update update = new Update();
        update.set("cv", cv);
        mongoTemplate.updateFirst(query, update, User.class);
        return skill;
    }

    @Override
    public ProfessionalExperience insertProfExp(int userId, ProfessionalExperience profExp){
        int newId = 0;

        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        CV cv = user.getCV();

        if(cv == null) {
            cv = new CV();
            newId = 1;
        }
        else{
            List<ProfessionalExperience> skills = (List<ProfessionalExperience>) Utilities.copy(cv.getProfessionalExperience());
            skills.sort(Comparator.comparing(ProfessionalExperience::getId));
            newId = skills.get(skills.size() - 1).getId() + 1;
        }

        profExp.setId(newId);
        cv.getProfessionalExperience().add(profExp);

        Update update = new Update();
        update.set("cv", cv);
        mongoTemplate.updateFirst(query, update, User.class);
        return profExp;
    }

    @Override
    public int updateContact(int userId, Contact contact){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();
        CV cv = user.getCV();
        cv.setContact(contact);

        Update update = new Update();
        update.set("cv", cv);
        mongoTemplate.updateFirst(query, update, User.class);
        return Response.Status.OK.getStatusCode();
    }

    @Override
    public int updateCert(int userId, Certification newCert){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();
        CV cv = user.getCV();
        if(Certification.updateCert(cv.getCertification(), newCert) == Response.Status.OK.getStatusCode()){
            Update update = new Update();
            update.set("cv", cv);
            mongoTemplate.updateFirst(query, update, User.class);
            return Response.Status.OK.getStatusCode();
        }
        return Response.Status.NOT_FOUND.getStatusCode();
    }

    @Override
    public int updateEducation(int userId, Education newEdu){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();
        CV cv = user.getCV();
        if(Education.updateEdu(cv.getEducation(), newEdu) == Response.Status.OK.getStatusCode()){
            Update update = new Update();
            update.set("cv", cv);
            mongoTemplate.updateFirst(query, update, User.class);
            return Response.Status.OK.getStatusCode();
        }
        return Response.Status.NOT_FOUND.getStatusCode();
    }

    @Override
    public int updateLang(int userId, Language newLang){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();
        CV cv = user.getCV();
        if(Language.updateLang(cv.getLanguage(), newLang) == Response.Status.OK.getStatusCode()){
            Update update = new Update();
            update.set("cv", cv);
            mongoTemplate.updateFirst(query, update, User.class);
            return Response.Status.OK.getStatusCode();
        }
        return Response.Status.NOT_FOUND.getStatusCode();
    }

    @Override
    public int updateProfExp(int userId, ProfessionalExperience newProfExp){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();
        CV cv = user.getCV();
        if(ProfessionalExperience.updateProfExp(cv.getProfessionalExperience(), newProfExp) == Response.Status.OK.getStatusCode()){
            Update update = new Update();
            update.set("cv", cv);
            mongoTemplate.updateFirst(query, update, User.class);
            return Response.Status.OK.getStatusCode();
        }
        return Response.Status.NOT_FOUND.getStatusCode();
    }

    @Override
    public int updateSkill(int userId, Skill newSkill){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();
        CV cv = user.getCV();
        if(Skill.updateSkill(cv.getSkill(), newSkill) == Response.Status.OK.getStatusCode()){
            Update update = new Update();
            update.set("cv", cv);
            mongoTemplate.updateFirst(query, update, User.class);
            return Response.Status.OK.getStatusCode();
        }
        return Response.Status.NOT_FOUND.getStatusCode();
    }

    @Override
    public int deleteCert(int userId, int certId){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();

        CV cv = user.getCV();
        cv.getCertification().remove(Certification.getCert(cv.getCertification(), certId));
        Update update = new Update();
        update.set("cv", cv);
        mongoTemplate.updateFirst(query, update, User.class);
        return Response.Status.OK.getStatusCode();
    }

    @Override
    public int deleteEducation(int userId, int eduId){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();

        CV cv = user.getCV();
        cv.getEducation().remove(Education.getEdu(cv.getEducation(), eduId));
        Update update = new Update();
        update.set("cv", cv);
        mongoTemplate.updateFirst(query, update, User.class);
        return Response.Status.OK.getStatusCode();
    }

    @Override
    public int deleteLang(int userId, int langId){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();

        CV cv = user.getCV();
        cv.getLanguage().remove(Language.getLang(cv.getLanguage(), langId));
        Update update = new Update();
        update.set("cv", cv);
        mongoTemplate.updateFirst(query, update, User.class);
        return Response.Status.OK.getStatusCode();
    }

    @Override
    public int deleteProfExp(int userId, int profExpId){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();

        CV cv = user.getCV();
        cv.getProfessionalExperience().remove(ProfessionalExperience.getProfExp(cv.getProfessionalExperience(), profExpId));
        Update update = new Update();
        update.set("cv", cv);
        mongoTemplate.updateFirst(query, update, User.class);
        return Response.Status.OK.getStatusCode();
    }

    @Override
    public int deleteSkill(int userId, int skillId){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();

        CV cv = user.getCV();
        cv.getSkill().remove(Skill.getSkill(cv.getSkill(), skillId));
        Update update = new Update();
        update.set("cv", cv);
        mongoTemplate.updateFirst(query, update, User.class);
        return Response.Status.OK.getStatusCode();
    }

}

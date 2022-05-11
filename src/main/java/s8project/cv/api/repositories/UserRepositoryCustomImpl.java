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
    public CV insertCV(int userId){
        int newId ;
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return null;
        if(user.getCV().size() == 0 ) newId = 1;
        else{
            newId = user.getCVMaxId() + 1;
        }
        CV cv = new CV();
        cv.setId(newId);
        user.getCV().add(cv);

        Update update = new Update();
        update.set("cv", user.getCV());
        mongoTemplate.updateFirst(query, update, User.class);
        return cv;
    }

    @Override
    public Certification insertCert(int userId, int cvId, Certification certification){
        int newId;

        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return null;
        CV cv = user.getCV(cvId);
        if(cv == null) return null;

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
        update.set("cv", user.getCV());
        mongoTemplate.updateFirst(query, update, User.class);
        return certification;
    }

    @Override
    public Contact insertContact(int userId, int cvId, Contact contact){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return null;
        CV cv = user.getCV(cvId);
        if(cv == null) return null;
        cv.setContact(contact);

        Update update = new Update();
        update.set("cv", user.getCV());
        mongoTemplate.updateFirst(query, update, User.class);
        return contact;
    }

    @Override
    public Education insertEducation(int userId, int cvId, Education education){
        int newId;

        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return null;
        CV cv = user.getCV(cvId);
        if(cv == null) return null;

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
        update.set("cv", user.getCV());
        mongoTemplate.updateFirst(query, update, User.class);
        return education;
    }

    @Override
    public Language insertLang(int userId, int cvId, Language lang){
        int newId;

        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return null;
        CV cv = user.getCV(cvId);
        if(cv == null) return null;

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
        update.set("cv", user.getCV());
        mongoTemplate.updateFirst(query, update, User.class);
        return lang;
    }

    @Override
    public Skill insertSkill(int userId, int cvId, Skill skill){
        int newId;

        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return null;
        CV cv = user.getCV(cvId);
        if(cv == null) return null;

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
        update.set("cv", user.getCV());
        mongoTemplate.updateFirst(query, update, User.class);
        return skill;
    }

    @Override
    public ProfessionalExperience insertProfExp(int userId, int cvId, ProfessionalExperience profExp){
        int newId;

        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return null;
        CV cv = user.getCV(cvId);
        if(cv == null) return null;

        else{
            List<ProfessionalExperience> skills = (List<ProfessionalExperience>) Utilities.copy(cv.getProfessionalExperience());
            if(skills.size() != 0) {
                skills.sort(Comparator.comparing(ProfessionalExperience::getId));
                newId = skills.get(skills.size() - 1).getId() + 1;
            }
            else{
                newId = 1;
            }
        }

        profExp.setId(newId);
        cv.getProfessionalExperience().add(profExp);

        Update update = new Update();
        update.set("cv", user.getCV());
        mongoTemplate.updateFirst(query, update, User.class);
        return profExp;
    }

    @Override
    public int updateContact(int userId, int cvId, Contact contact){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();
        CV cv = user.getCV(cvId);
        cv.setContact(contact);

        Update update = new Update();
        update.set("cv", user.getCV());
        mongoTemplate.updateFirst(query, update, User.class);
        return Response.Status.OK.getStatusCode();
    }

    @Override
    public int updateCert(int userId, int cvId, Certification newCert){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();
        CV cv = user.getCV(cvId);
        if(cv.updateCert(newCert) == Response.Status.OK.getStatusCode()){
            Update update = new Update();
            update.set("cv", user.getCV());
            mongoTemplate.updateFirst(query, update, User.class);
            return Response.Status.OK.getStatusCode();
        }
        return Response.Status.NOT_FOUND.getStatusCode();
    }

    @Override
    public int updateEducation(int userId, int cvId, Education newEdu){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();
        CV cv = user.getCV(cvId);
        if(cv.updateEdu(newEdu) == Response.Status.OK.getStatusCode()){
            Update update = new Update();
            update.set("cv", user.getCV());
            mongoTemplate.updateFirst(query, update, User.class);
            return Response.Status.OK.getStatusCode();
        }
        return Response.Status.NOT_FOUND.getStatusCode();
    }

    @Override
    public int updateLang(int userId, int cvId, Language newLang){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();
        CV cv = user.getCV(cvId);
        if(cv.updateLang(newLang) == Response.Status.OK.getStatusCode()){
            Update update = new Update();
            update.set("cv", user.getCV());
            mongoTemplate.updateFirst(query, update, User.class);
            return Response.Status.OK.getStatusCode();
        }
        return Response.Status.NOT_FOUND.getStatusCode();
    }

    @Override
    public int updateProfExp(int userId, int cvId, ProfessionalExperience newProfExp){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();
        CV cv = user.getCV(cvId);
        if(cv.updateProfExp(newProfExp) == Response.Status.OK.getStatusCode()){
            Update update = new Update();
            update.set("cv", user.getCV());
            mongoTemplate.updateFirst(query, update, User.class);
            return Response.Status.OK.getStatusCode();
        }
        return Response.Status.NOT_FOUND.getStatusCode();
    }

    @Override
    public int updateSkill(int userId, int cvId, Skill newSkill){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();
        CV cv = user.getCV(cvId);
        if(cv.updateSkill(newSkill) == Response.Status.OK.getStatusCode()){
            Update update = new Update();
            update.set("cv", user.getCV());
            mongoTemplate.updateFirst(query, update, User.class);
            return Response.Status.OK.getStatusCode();
        }
        return Response.Status.NOT_FOUND.getStatusCode();
    }

    @Override
    public int deleteCert(int userId, int cvId, int certId){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();

        CV cv = user.getCV(cvId);
        cv.getCertification().remove(cv.getCertification(certId));
        Update update = new Update();
        update.set("cv", user.getCV());
        mongoTemplate.updateFirst(query, update, User.class);
        return Response.Status.OK.getStatusCode();
    }

    @Override
    public int deleteEducation(int userId, int cvId, int eduId){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();

        CV cv = user.getCV(cvId);
        cv.getEducation().remove(cv.getEducation(eduId));
        Update update = new Update();
        update.set("cv", user.getCV());
        mongoTemplate.updateFirst(query, update, User.class);
        return Response.Status.OK.getStatusCode();
    }

    @Override
    public int deleteLang(int userId, int cvId, int langId){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();

        CV cv = user.getCV(cvId);
        cv.getLanguage().remove(cv.getLanguage(langId));
        Update update = new Update();
        update.set("cv", user.getCV());
        mongoTemplate.updateFirst(query, update, User.class);
        return Response.Status.OK.getStatusCode();
    }

    @Override
    public int deleteProfExp(int userId, int cvId, int profExpId){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();

        CV cv = user.getCV(cvId);
        cv.getProfessionalExperience().remove(cv.getProfessionalExperience(profExpId));
        Update update = new Update();
        update.set("cv", user.getCV());
        mongoTemplate.updateFirst(query, update, User.class);
        return Response.Status.OK.getStatusCode();
    }

    @Override
    public int deleteSkill(int userId, int cvId, int skillId){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if(user == null) return Response.Status.NOT_FOUND.getStatusCode();

        CV cv = user.getCV(cvId);
        cv.getSkill().remove(cv.getSkill(skillId));
        Update update = new Update();
        update.set("cv", user.getCV());
        mongoTemplate.updateFirst(query, update, User.class);
        return Response.Status.OK.getStatusCode();
    }

}

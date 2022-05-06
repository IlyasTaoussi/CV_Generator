package s8project.cv.api.repositories;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import s8project.cv.api.documents.*;
import s8project.cv.api.utilities.Utilities;


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
            certs.sort(Comparator.comparing(Certification::getId));
            newId = certs.get(certs.size() - 1).getId() + 1;
        }

        certification.setId(newId);
        cv.getCertification().add(certification);

        Update update = new Update();
        update.set("cv", cv);
        mongoTemplate.updateFirst(query, update, User.class);
        return certification;
    }

    @Override
    public void insertContact(int userId, Contact contact){
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        CV cv = user.getCV();

        if(cv == null) cv = new CV();
        cv.setContact(contact);

        Update update = new Update();
        update.set("cv", cv);
        mongoTemplate.updateFirst(query, update, User.class);
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
            List<Education> educations = (List<Education>) Utilities.copy(cv.getCertification());
            educations.sort(Comparator.comparing(Education::getId));
            newId = educations.get(educations.size() - 1).getId() + 1;
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
            List<Language> langs = (List<Language>) Utilities.copy(cv.getCertification());
            langs.sort(Comparator.comparing(Language::getId));
            newId = langs.get(langs.size() - 1).getId() + 1;
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
            List<Skill> skills = (List<Skill>) Utilities.copy(cv.getCertification());
            skills.sort(Comparator.comparing(Skill::getId));
            newId = skills.get(skills.size() - 1).getId() + 1;
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
            List<Skill> skills = (List<Skill>) Utilities.copy(cv.getCertification());
            skills.sort(Comparator.comparing(Skill::getId));
            newId = skills.get(skills.size() - 1).getId() + 1;
        }

        profExp.setId(newId);
        cv.getProfessionalExperience().add(profExp);

        Update update = new Update();
        update.set("cv", cv);
        mongoTemplate.updateFirst(query, update, User.class);
        return profExp;
    }

}

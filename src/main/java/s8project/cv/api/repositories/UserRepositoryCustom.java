package s8project.cv.api.repositories;

import s8project.cv.api.documents.*;


public interface UserRepositoryCustom {

    User findByUserId(int userId);
    int getMaxUserId();

    Certification insertCert(int userId, Certification certification);
    void insertContact(int userId, Contact contact);
    Education insertEducation(int userId, Education education);
    Language insertLang(int userId, Language lang);
    ProfessionalExperience insertProfExp(int userId, ProfessionalExperience profExp);
    Skill insertSkill(int userId, Skill skill);

    int updateCert(int userId, int certId, Certification certification);
    int updateEducation(int userId, int eduId, Education newEdu);
    int updateLang(int userId, int langId, Language newLang);
    int updateProfExp(int userId, int langId, ProfessionalExperience newProfExp);
    int updateSkill(int userId, Skill newSkill);
}

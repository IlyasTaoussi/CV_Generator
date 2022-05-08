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

    Certification updateCert(int userId, int certId, Certification certification);
    Education updateEducation(int userId, int eduId, Education newEdu);
    Language updateLang(int userId, int langId, Language newLang);
    ProfessionalExperience updateProfExp(int userId, int langId, ProfessionalExperience newProfExp);
    Skill updateSkill(int userId, int skillId, Skill newSkill);
}

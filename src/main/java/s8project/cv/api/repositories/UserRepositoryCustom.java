package s8project.cv.api.repositories;

import s8project.cv.api.documents.*;


public interface UserRepositoryCustom {

    User findByUserId(int userId);
    int getMaxUserId();

    Certification getCert(int userId, int certId);
    Contact getContact(int userId);
    Education getEducation(int userId, int eduId);
    Language getLang(int userId, int langId);
    ProfessionalExperience getProfExp(int userId, int profExpId);
    Skill getSkill(int userId, int skillId);

    Certification insertCert(int userId, Certification certification);
    Contact insertContact(int userId, Contact contact);
    Education insertEducation(int userId, Education education);
    Language insertLang(int userId, Language lang);
    ProfessionalExperience insertProfExp(int userId, ProfessionalExperience profExp);
    Skill insertSkill(int userId, Skill skill);

    int updateCert(int userId, Certification certification);
    int updateContact(int userid, Contact contact);
    int updateEducation(int userId, Education newEdu);
    int updateLang(int userId, Language newLang);
    int updateProfExp(int userId, ProfessionalExperience newProfExp);
    int updateSkill(int userId, Skill newSkill);

    int deleteCert(int userId, int certId);
    int deleteEducation(int userId, int eduId);
    int deleteLang(int userId, int langId);
    int deleteProfExp(int userId, int profExpId);
    int deleteSkill(int userId, int skillId);
}

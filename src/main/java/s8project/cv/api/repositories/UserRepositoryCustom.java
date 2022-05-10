package s8project.cv.api.repositories;

import s8project.cv.api.documents.*;


public interface UserRepositoryCustom {

    User findByUserId(int userId);
    int getMaxUserId();

    CV insertCV(int userid);
    Certification insertCert(int userId, int cvId, Certification certification);
    Contact insertContact(int userId, int cvId, Contact contact);
    Education insertEducation(int userId, int cvId, Education education);
    Language insertLang(int userId, int cvId, Language lang);
    ProfessionalExperience insertProfExp(int userId, int cvId, ProfessionalExperience profExp);
    Skill insertSkill(int userId, int cvId, Skill skill);

    int updateCert(int userId, int cvId, Certification certification);
    int updateContact(int userid, int cvId, Contact contact);
    int updateEducation(int userId, int cvId, Education newEdu);
    int updateLang(int userId, int cvId, Language newLang);
    int updateProfExp(int userId, int cvId, ProfessionalExperience newProfExp);
    int updateSkill(int userId, int cvId, Skill newSkill);

    int deleteCert(int userId, int cvId, int certId);
    int deleteEducation(int userId, int cvId, int eduId);
    int deleteLang(int userId, int cvId, int langId);
    int deleteProfExp(int userId, int cvId, int profExpId);
    int deleteSkill(int userId, int cvId, int skillId);
}

package s8project.cv.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import s8project.cv.api.documents.*;
import s8project.cv.api.inputs.UserInput;
import s8project.cv.api.repositories.UserRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("user")
public class UserRest {

    @Autowired
    private UserRepository userRepository;

    /**
     * GET API CALLS
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("search")
    public Response getUserByMail(@QueryParam("mail") String mail){
        Optional<User> optU = userRepository.findByMail(mail);
        if(optU.isEmpty()) return Response.status(Response.Status.NOT_FOUND).build();
        User user = optU.get();
        return Response.ok(user).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}")
    public Response getUserData(@PathParam("userId") int userId){
        User user = userRepository.findByUserId(userId);
        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(user).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/cert")
    public Response getCert(@PathParam("userId") int userId, @QueryParam("cv") int cvId, @QueryParam("cert") int certId){
        User user = userRepository.findByUserId(userId);
        if(user == null || user.getCV(cvId) == null || user.getCV(cvId).getCertification(certId) == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(user.getCV(cvId).getCertification(certId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/contact")
    public Response getContact(@PathParam("userId") int userId, @PathParam("cv") int cvId){
        User user = userRepository.findByUserId(userId);
        if(user == null || user.getCV(cvId) == null || user.getCV(cvId).getContact() == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(user.getCV(cvId).getContact()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/education")
    public Response getEducation(@PathParam("userId") int userId, @QueryParam("cv") int cvId, @QueryParam("edu") int eduId){
        User user = userRepository.findByUserId(userId);
        if(user == null || user.getCV(cvId) == null || user.getCV(cvId).getEducation(eduId) == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(user.getCV(cvId).getEducation(eduId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/language")
    public Response getLang(@PathParam("userId") int userId, @QueryParam("cv") int cvId, @QueryParam("langId") int langId){
        User user = userRepository.findByUserId(userId);
        if(user == null || user.getCV(cvId) == null || user.getCV(cvId).getLanguage(langId) == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(user.getCV(cvId).getLanguage(langId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/profExp")
    public Response getProfExp(@PathParam("userId") int userId, @QueryParam("cv") int cvId, @QueryParam("profExp") int profExpId){
        User user = userRepository.findByUserId(userId);
        if(user == null || user.getCV(cvId) == null || user.getCV(cvId).getProfessionalExperience(profExpId) == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(user.getCV(cvId).getProfessionalExperience(profExpId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/skill")
    public Response getSkill(@PathParam("userId") int userId, @QueryParam("cv") int cvId, @QueryParam("skill") int skillId){
        User user = userRepository.findByUserId(userId);
        if(user == null || user.getCV(cvId) == null || user.getCV(cvId).getSkill(skillId) == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(user.getCV(cvId).getSkill(skillId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/cv")
    public Response getCV(@PathParam("userId") int userId, @QueryParam("cv") int cvId){
        User user = userRepository.findByUserId(userId);
        if(user == null || user.getCV(cvId) == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(user.getCV(cvId)).build();
    }

    /**
     * POST API CALLS
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("new")
    public Response insertUser(UserInput input){
        int id = userRepository.getMaxUserId() + 1;
        User user = new User(input.getMail(), input.getPassword());
        user.setUserId(id);
        userRepository.insert(user);
        return Response.ok(user).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("search")
    public Response searchUser(UserInput input){
        Optional<User> optU = userRepository.findByMailAndPassword(input.getMail(), input.getPassword());
        if(optU.isEmpty()) return Response.status(Response.Status.UNAUTHORIZED).build();
        User user = optU.get();
        return Response.ok(user).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{userId}/cv/new")
    public Response insertCV(@PathParam("userId") int userId){
        User user = userRepository.findByUserId(userId);
        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(userRepository.insertCV(userId)).build();
    }


    /**
     * PUT API CALLS
     */

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/cert/new")
    public Response insertCert(@PathParam("userId") int userId, @QueryParam("cv") int cvId,  Certification certification){
        User user = userRepository.findByUserId(userId);
        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        userRepository.insertCert(userId, cvId, certification);
        return Response.ok(certification).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/contact/new")
    public Response insertContact(@PathParam("userId") int userId, @QueryParam("cv") int cvId, Contact contact){
        User user = userRepository.findByUserId(userId);

        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        contact = userRepository.insertContact(userId, cvId, contact);
        if(contact == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(contact).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/profExp/new")
    public Response insertProfExp(@PathParam("userId") int userId, @QueryParam("cv") int cvId, ProfessionalExperience profExp){
        User user = userRepository.findByUserId(userId);

        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        userRepository.insertProfExp(userId, cvId, profExp);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/skill/new")
    public Response insertSkill(@PathParam("userId") int userId, @QueryParam("cv") int cvId, Skill skill){
        User user = userRepository.findByUserId(userId);

        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        userRepository.insertSkill(userId, cvId, skill);
        return Response.ok(skill).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/education/new")
    public Response insertEducation(@PathParam("userId") int userId, @QueryParam("cv") int cvId, Education education){
        User user = userRepository.findByUserId(userId);

        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        userRepository.insertEducation(userId, cvId, education);
        return Response.ok(education).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/lang/new")
    public Response insertLanguage(@PathParam("userId") int userId, @QueryParam("cv") int cvId, Language lang){
        User user = userRepository.findByUserId(userId);
        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();

        userRepository.insertLang(userId, cvId, lang);
        return Response.ok(lang).build();
    }

    /**
     * PATCH API CALLS
     */

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/contact/update")
    public Response updateContact(@PathParam("userId") int userId, @QueryParam("cv") int cvId, Contact contact){
        User user = userRepository.findByUserId(userId);
        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();

        int status = userRepository.updateContact(userId, cvId, contact);
        if(status == Response.Status.OK.getStatusCode()) Response.ok(contact).build();
        return Response.status(status).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/cert/update")
    public Response updateCert(@PathParam("userId") int userId, @QueryParam("cv") int cvId, @PathParam("certId") int certId, Certification certification){
        certification.setId(certId);
        int status = userRepository.updateCert(userId, cvId, certification);
        if(status == Response.Status.OK.getStatusCode()) Response.ok(certification).build();
        return Response.status(status).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/education/update")
    public Response updateEducation(@PathParam("userId") int userId,@QueryParam("cv") int cvId, @PathParam("eduId") int eduId, Education education){
        education.setId(eduId);
        int status = userRepository.updateEducation(userId, cvId, education);
        if(status == Response.Status.OK.getStatusCode()) Response.ok(education).build();
        return Response.status(status).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/language/update")
    public Response updateLang(@PathParam("userId") int userId, @QueryParam("cv") int cvId, @PathParam("langId") int langId, Language lang){
        lang.setId(langId);
        int status = userRepository.updateLang(userId, cvId, lang);
        if(status == Response.Status.OK.getStatusCode()) return Response.ok(lang).build();
        return Response.status(status).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/profExp/update")
    public Response updateProfExp(@PathParam("userId") int userId, @QueryParam("cv") int cvId, @PathParam("profExpId") int profExpId, ProfessionalExperience profExp){
        profExp.setId(profExpId);
        int status = userRepository.updateProfExp(userId, cvId, profExp);
        if(status == Response.Status.OK.getStatusCode())  Response.ok(profExp).build();
        return Response.status(status).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/skill/update")
    public Response updateSkill(@PathParam("userId") int userId, @QueryParam("cv") int cvId, @PathParam("skillId") int skillId, Skill skill){
        skill.setId(skillId);
        int status = userRepository.updateSkill(userId, cvId, skill);
        if(status == Response.Status.OK.getStatusCode()) return Response.ok(skill).build();
        return Response.status(status).build();
    }

    /**
     * DELETE API CALLS
     */

    @DELETE
    @Path("{userId}/cert/del")
    public Response deleteCert(@PathParam("userId") int userId, @QueryParam("cv") int cvId, @PathParam("certId") int certId){
        userRepository.deleteCert(userId, cvId, certId);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{userId}/education/delete")
    public Response deleteEducation(@PathParam("userId") int userId, @QueryParam("cv") int cvId, @PathParam("eduId") int eduId){
        userRepository.deleteEducation(userId, cvId, eduId);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{userId}/language/delete")
    public Response deleteLang(@PathParam("userId") int userId, @QueryParam("cv") int cvId, @PathParam("langId") int langId){
        userRepository.deleteLang(userId, cvId, langId);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{userId}/profExp/delete")
    public Response deleteProfExp(@PathParam("userId") int userId, @QueryParam("cv") int cvId, @PathParam("ProfExpId") int profExpId){
        userRepository.deleteProfExp(userId, cvId, profExpId);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{userId}/skill/delete")
    public Response deleteSkill(@PathParam("userId") int userId, @QueryParam("cv") int cvId, @PathParam("skillId") int skillId){
        userRepository.deleteSkill(userId, cvId, skillId);
        return Response.status(Response.Status.OK).build();
    }

}

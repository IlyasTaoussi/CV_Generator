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
        if(!optU.isPresent()) return Response.status(Response.Status.NOT_FOUND).build();
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
    @Path("{userId}/cert/{certId}")
    public Response getCert(@PathParam("userId") int userId, @PathParam("certId") int certId){
        User user = userRepository.findByUserId(userId);
        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(userRepository.getCert(userId, certId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/contact")
    public Response getContact(@PathParam("userId") int userId){
        User user = userRepository.findByUserId(userId);
        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(userRepository.getContact(userId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/education/{eduId}")
    public Response getEducation(@PathParam("userId") int userId, @PathParam("eduId") int eduId){
        User user = userRepository.findByUserId(userId);
        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(userRepository.getEducation(userId, eduId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/language/{langId}")
    public Response getLang(@PathParam("userId") int userId, @PathParam("langId") int langId){
        User user = userRepository.findByUserId(userId);
        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(userRepository.getLang(userId, langId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/profExp/{profExpId}")
    public Response getProfExp(@PathParam("userId") int userId, @PathParam("profExpId") int profExpId){
        User user = userRepository.findByUserId(userId);
        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(userRepository.getProfExp(userId, profExpId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/skill/{skillId}")
    public Response getSkill(@PathParam("userId") int userId, @PathParam("skillId") int skillId){
        User user = userRepository.findByUserId(userId);
        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(userRepository.getSkill(userId, skillId)).build();
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
        User user = new User(id, input.getMail(), input.getPassword());
        userRepository.insert(user);
        return Response.ok(user).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("search")
    public Response searchUser(UserInput input){
        Optional<User> optU = userRepository.findByMailAndPassword(input.getMail(), input.getPassword());
        if(!optU.isPresent()) return Response.status(Response.Status.UNAUTHORIZED).build();
        User user = optU.get();
        return Response.ok(user).build();
    }


    /**
     * PUT API CALLS
     */

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/cert/new")
    public Response insertCert(@PathParam("userId") int userId, Certification certification){
        User user = userRepository.findByUserId(userId);
        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        userRepository.insertCert(userId, certification);
        return Response.ok(certification).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/contact/new")
    public Response insertContact(@PathParam("userId") int userId, Contact contact){
        User user = userRepository.findByUserId(userId);

        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        userRepository.insertContact(userId, contact);
        return Response.ok(contact).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/profExp/new")
    public Response insertProfExp(@PathParam("userId") int userId, ProfessionalExperience profExp){
        User user = userRepository.findByUserId(userId);

        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        userRepository.insertProfExp(userId, profExp);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/skill/new")
    public Response insertSkill(@PathParam("userId") int userId, Skill skill){
        User user = userRepository.findByUserId(userId);

        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        userRepository.insertSkill(userId, skill);
        return Response.ok(skill).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/education/new")
    public Response insertEducation(@PathParam("userId") int userId, Education education){
        User user = userRepository.findByUserId(userId);

        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        userRepository.insertEducation(userId, education);
        return Response.ok(education).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/lang/new")
    public Response insertLanguage(@PathParam("userId") int userId, Language lang){
        User user = userRepository.findByUserId(userId);
        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();

        userRepository.insertLang(userId, lang);
        return Response.ok(lang).build();
    }

    /**
     * PATCH API CALLS
     */

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/contact")
    public Response updateContact(@PathParam("userId") int userId, Contact contact){
        User user = userRepository.findByUserId(userId);
        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();

        int status = userRepository.updateContact(userId, contact);
        if(status == Response.Status.OK.getStatusCode()) Response.ok(contact).build();
        return Response.status(status).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/cert/{certId}")
    public Response updateCert(@PathParam("userId") int userId, @PathParam("certId") int certId, Certification certification){
        certification.setId(certId);
        int status = userRepository.updateCert(userId, certification);
        if(status == Response.Status.OK.getStatusCode()) Response.ok(certification).build();
        return Response.status(status).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/education/{eduId}")
    public Response updateEducation(@PathParam("userId") int userId, @PathParam("eduId") int eduId, Education education){
        education.setId(eduId);
        int status = userRepository.updateEducation(userId, education);
        if(status == Response.Status.OK.getStatusCode()) Response.ok(education).build();
        return Response.status(status).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/lang/{langId}")
    public Response updateLang(@PathParam("userId") int userId, @PathParam("langId") int langId, Language lang){
        lang.setId(langId);
        int status = userRepository.updateLang(userId, lang);
        if(status == Response.Status.OK.getStatusCode()) return Response.ok(lang).build();
        return Response.status(status).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/profExp/{profExpId}")
    public Response updateProfExp(@PathParam("userId") int userId, @PathParam("profExpId") int profExpId, ProfessionalExperience profExp){
        profExp.setId(profExpId);
        int status = userRepository.updateProfExp(userId, profExp);
        if(status == Response.Status.OK.getStatusCode())  Response.ok(profExp).build();
        return Response.status(status).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/skill/{skillId}")
    public Response updateSkill(@PathParam("userId") int userId, @PathParam("skillId") int skillId, Skill skill){
        skill.setId(skillId);
        int status = userRepository.updateSkill(userId, skill);
        if(status == Response.Status.OK.getStatusCode()) return Response.ok(skill).build();
        return Response.status(status).build();
    }

    /**
     * DELETE API CALLS
     */

    @DELETE
    @Path("{userId}/cert/{certId}")
    public Response deleteCert(@PathParam("userId") int userId, @PathParam("certId") int certId){
        userRepository.deleteCert(userId, certId);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{userId}/edu/{eduId}")
    public Response deleteEducation(@PathParam("userId") int userId, @PathParam("eduId") int eduId){
        userRepository.deleteEducation(userId, eduId);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{userId}/language/{langId}")
    public Response deleteLang(@PathParam("userId") int userId, @PathParam("langId") int langId){
        userRepository.deleteLang(userId, langId);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{userId}/profExp/{profExpId}")
    public Response deleteProfExp(@PathParam("userId") int userId, @PathParam("ProfExpId") int profExpId){
        userRepository.deleteProfExp(userId, profExpId);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{userId}/skill/{skillId}")
    public Response deleteSkill(@PathParam("userId") int userId, @PathParam("skillId") int skillId){
        userRepository.deleteSkill(userId, skillId);
        return Response.status(Response.Status.OK).build();
    }

}

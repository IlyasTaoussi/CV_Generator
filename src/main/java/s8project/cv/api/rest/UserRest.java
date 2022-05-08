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

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/cert/{certId}")
    public Response updateCert(@PathParam("userId") int userId, @PathParam("certId") int certId, Certification certification){
        User user = userRepository.findByUserId(userId);

        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        userRepository.updateCert(userId, certId, certification);
        return Response.ok(certification).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/education/{eduId}")
    public Response updateEducation(@PathParam("userId") int userId, @PathParam("eduId") int eduId, Education education){
        User user = userRepository.findByUserId(userId);

        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        userRepository.updateEducation(userId, eduId, education);
        return Response.ok(education).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/lang/{langId}")
    public Response updateLang(@PathParam("userId") int userId, @PathParam("langId") int langId, Language lang){
        User user = userRepository.findByUserId(userId);

        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        userRepository.updateLang(userId, langId, lang);
        return Response.ok(lang).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/profExp/{profExpId}")
    public Response updateProfExp(@PathParam("userId") int userId, @PathParam("profExpId") int profExpId, ProfessionalExperience profExp){
        User user = userRepository.findByUserId(userId);

        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        userRepository.updateProfExp(userId, profExpId, profExp);
        return Response.ok(profExp).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}/skill/{skillId}")
    public Response updateSkill(@PathParam("userId") int userId, @PathParam("skillId") int skillId, Skill skill){
        User user = userRepository.findByUserId(userId);

        if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
        userRepository.updateSkill(userId, skillId, skill);
        return Response.ok(skill).build();
    }

}

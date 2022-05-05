package s8project.cv.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import s8project.cv.api.documents.User;
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
    public Response createUser(UserInput input){
        User user = new User(input);
        userRepository.insert(user);
        return Response.ok(user).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("search")
    public Response searchUser(UserInput input){
        Optional<User> optU = userRepository.findByMailAndPassword(input.getMail(), input.getPassword());
        if(!optU.isPresent()) return Response.status(Response.Status.NOT_FOUND).build();
        User user = optU.get();
        return Response.ok(user).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("search")
    public Response getUserByMail(@RequestParam("mail") String mail){
        Optional<User> optU = userRepository.findByMail(mail);
        if(!optU.isPresent()) return Response.status(Response.Status.NOT_FOUND).build();
        User user = optU.get();
        return Response.ok(user).build();
    }

}

package s8project.cv.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import s8project.cv.api.documents.User;
import s8project.cv.api.documents.UserInput;
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

}

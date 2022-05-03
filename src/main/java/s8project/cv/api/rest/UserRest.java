package s8project.cv.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import s8project.cv.api.documents.User;
import s8project.cv.api.repositories.UserRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("user")
public class UserRest {

    @Autowired
    private UserRepository userRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}")
    public Response getUser(@PathParam("userId") int userId){
        Optional<User> optU = userRepository.findById(userId);
        if(!optU.isPresent()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        User user = optU.get();
        return Response.ok(user).build();
    }
}

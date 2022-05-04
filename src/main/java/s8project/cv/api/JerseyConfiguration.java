package s8project.cv.api;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import s8project.cv.api.rest.UserRest;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("api")
public class JerseyConfiguration extends ResourceConfig {

        public JerseyConfiguration(){ register(UserRest.class); }
}

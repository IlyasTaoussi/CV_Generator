package s8project.cv.api;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("generator")
public class JerseyConfiguration extends ResourceConfig {

        public JerseyConfiguration(){

        }
}

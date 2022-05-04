package s8project.cv.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import s8project.cv.api.documents.User;

public interface UserRepository extends MongoRepository<User, String> {
}

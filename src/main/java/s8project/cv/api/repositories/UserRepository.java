package s8project.cv.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import s8project.cv.api.documents.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByMailAndPassword(String mail, String password);

    Optional<User> findByMail(String mail);

    Optional<User> findByPassword(String password);
}

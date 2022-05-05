package s8project.cv.api.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import s8project.cv.api.documents.User;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public int getMaxUserId() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "userId"));
        query.limit(1);
        User user = mongoTemplate.findOne(query, User.class);
        if (user == null) {
            return 0;
        }
        return user.getUserId();
    }

}

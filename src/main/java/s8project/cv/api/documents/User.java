package s8project.cv.api.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="user")
public class User {

    @Indexed(unique=true)
    @Field(value="mail")
    @Id
    private String mail;

    @Field(value="password")
    private String password;

    @Field(value="cv")
    private CV CV;

    public User(UserInput input) {
        mail = input.getMail();
        password = input.getPassword();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public s8project.cv.api.documents.CV getCV() {
        return CV;
    }

    public void setCV(s8project.cv.api.documents.CV CV) {
        this.CV = CV;
    }
}

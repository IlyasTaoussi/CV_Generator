package s8project.cv.api.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import s8project.cv.api.inputs.UserInput;

@Document(collection="user")
public class User {

    @Id
    @Field(value="_id")
    @Indexed(unique=true)
    private String id;

    @Indexed(unique=true)
    @Field(value="mail")
    private String mail;

    @Field(value="password")
    private String password;

    @Field(value="cv")
    private CV CV;

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
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
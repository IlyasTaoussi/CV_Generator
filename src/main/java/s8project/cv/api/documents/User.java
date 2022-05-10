package s8project.cv.api.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import s8project.cv.api.inputs.UserInput;

import java.util.ArrayList;
import java.util.List;

@Document(collection="user")
public class User {

    @Field(value="userId")
    @Indexed(unique=true)
    private int userId;

    @Indexed(unique=true)
    @Field(value="mail")
    private String mail;

    @Field(value="password")
    private String password;

    @Field(value="cv")
    private List<CV> CV;

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
        this.CV = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public List<s8project.cv.api.documents.CV> getCV() {
        return CV;
    }

    public s8project.cv.api.documents.CV getCV(int cvId) {
        for(CV cv: CV) {
            if(cv.getId() == cvId) return cv;
        }
        return null;
    }

    public int getCVMaxId(){
        int maxId = 0;
        for(CV cv : CV){
            if(cv.getId() > maxId) maxId = cv.getId();
        }
        return maxId;
    }

    public void setCV(List<s8project.cv.api.documents.CV> CV) {
        this.CV = CV;
    }

}

package s8project.cv.api.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document("contact")
public class Contact {

    @Field(value="mail")
    private String mail;

    @Field(value="name")
    private String name;

    @Field(value="adress")
    private String address;

    @Field(value="phone_number")
    private String phoneNumber;

    @Field(value="links")
    private List<String> links;

    public Contact(){
        links = new ArrayList<>();
    }

    public Contact(String mail, String name, String address, String phoneNumber, List<String> links) {
        this.mail = mail;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.links = links;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}

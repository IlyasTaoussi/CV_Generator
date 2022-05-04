package s8project.cv.api.documents;

import java.util.List;


public class Contact {

    private String mail;
    private String name;
    private String address;
    private String phoneNumber;
    private List<String> links;

    public Contact(String mail, String name, String address, String phoneNumber, List<String> links) {
        this.mail = mail;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.links = links;
    }

    public Contact(){ }

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

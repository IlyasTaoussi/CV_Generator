package s8project.cv.api.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.ws.rs.core.Response;
import java.util.List;

@Document("certification")
public class Certification {

    @Id
    @Field(value="certId")
    private int certId;

    @Field(value = "name")
    private String name;

    @Field(value = "start_date")
    private String startDate;

    @Field(value = "end_date")
    private String endDate;

    @Field(value = "description")
    private String description;

    public Certification(String name, String startDate, String endDate, String description) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public int getId() {
        return certId;
    }

    public void setId(int certId) {
        this.certId = certId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Certification getCert(List<Certification> certs, int certId){
        for(Certification cert: certs) {
            if (cert.getId() == certId) return cert;
        }
        return null;
    }

    public static int updateCert(List<Certification> certs, Certification newCert){
        Certification cert = getCert(certs, newCert.getId());
        if(cert == null) return Response.Status.NOT_FOUND.getStatusCode();

        cert.setName(newCert.getName());
        cert.setDescription(newCert.getDescription());
        cert.setStartDate(newCert.getStartDate());
        cert.setEndDate(newCert.getEndDate());
        return Response.Status.OK.getStatusCode();

    }
}

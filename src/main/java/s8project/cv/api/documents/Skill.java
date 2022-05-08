package s8project.cv.api.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.ws.rs.core.Response;
import java.util.List;

@Document("skill")
public class Skill {

    @Id
    @Field(value="skillId")
    private int skillId;

    @Field(value="name")
    private String name;

    @Field(value="level")
    private String level;

    public int getId() {
        return skillId;
    }

    public void setId(int skillId) {
        this.skillId = skillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public static Skill getSkill(List<Skill> skills, int skillId){
        for(Skill skill: skills){
            if(skill.getId() == skillId) return skill;
        }
        return null;
    }

    public static int updateSkill(List<Skill> skills, Skill newSkill){
        Skill skill = getSkill(skills, newSkill.getId());
        if(skill == null) return Response.Status.NOT_FOUND.getStatusCode();

        skill.setName(newSkill.getName());
        skill.setLevel(newSkill.getLevel());
        return Response.Status.OK.getStatusCode();
    }
}

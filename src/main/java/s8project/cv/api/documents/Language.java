package s8project.cv.api.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document("language")
public class Language {
    @Id
    @Field(value="langId")
    private int langId;

    @Field(value="name")
    private String name;

    @Field(value="level")
    private String level;

    public int getId() {
        return langId;
    }

    public void setId(int langId) {
        this.langId = langId;
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

    public static void updateLang(List<Language> langs, Language newLang){
        for(Language lang: langs){
            if(lang.getId() == newLang.getId()) {
                lang.setName(newLang.getName());
                lang.setLevel(newLang.getLevel());
                break;
            };
        }
    }
}

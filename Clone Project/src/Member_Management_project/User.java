import javax.xml.namespace.QName;
import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String name;
    private int birthYear;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public User(String email, String name, int birthYear){
        this.email=email;
        this.name= name;
        this.birthYear=birthYear;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }
}

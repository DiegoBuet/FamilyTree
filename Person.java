import java.util.Random;

public class Person {
    protected String name;
    protected String lastName;

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

}
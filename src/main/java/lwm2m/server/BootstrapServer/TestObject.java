package lwm2m.server.BootstrapServer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jilongsun on 6/27/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestObject {
    @Override
    public String toString() {
        return "TestObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

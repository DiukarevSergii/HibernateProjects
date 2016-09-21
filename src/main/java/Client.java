import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "Clients")
@NamedQueries({
        @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
        @NamedQuery(name = "Client.findByAge", query = "SELECT c FROM Client c WHERE c.age > 8")
})
public class Client {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;
    private int age;

    @ManyToMany
    @JoinTable(
            name = "ClientCourse",
            joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id2")})

    List<Course> courses = new ArrayList<>();

    public Client() {
    }

    public Client(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void addCourse(Course course) {
        if (!courses.contains(course))
            courses.add(course);
        if (!course.clients.contains(this))
            course.clients.add(this);
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

package StreamAndCollectorsExamples;

import java.util.List;
import java.util.stream.Collectors;

class User{
    private  String name;
    private List<String> skills;
    public User(String name, List<String> skills){
        this.name = name;
        this.skills = skills;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getSkills() {
        return skills;
    }
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", skills=" + skills +
                '}';
    }
}
public class UsersFlattenMap {


    public static void main(String[] args) {
        User user1 = new User("Mahee", List.of("Java", "Python", "C++", "Rust"));
        User user2 = new User("Akshit", List.of("JavaScript", "HTML", "CSS", "Python"));
        User user3 = new User("Ram", List.of("Go", "Rust", "Kotlin", "HTML"));

        List<User> users = List.of(user1, user2, user3);

        List<String> userSkills = users.stream()
                        .map(u -> u.getSkills())
                        .flatMap( x -> x.stream())
                        .collect(Collectors.toList());

        System.out.println(userSkills.stream().distinct().sorted().toList());



    }


}

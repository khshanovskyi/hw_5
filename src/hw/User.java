package hw;

public class User {
    public Long id;
    private String firstName;
    private String lastName;
    private int age;
    private boolean employed;
    private String password;
    private Long first;
    public String second;
    public int third;
    private Long forth;

    public User(Long id, String firstName, String lastName, int age, boolean employed, String password, Long first,
                String second, int third, Long forth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.employed = employed;
        this.password = password;
        this.first = first;
        this.second = second;
        this.third = third;
        this.forth = forth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", employed=" + employed +
                ", password='" + password + '\'' +
                ", first=" + first +
                ", second='" + second + '\'' +
                ", third=" + third +
                ", forth=" + forth +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void someTestPublicMethod(){
        System.out.println("do nothing..");
    }
}

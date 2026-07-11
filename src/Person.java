//Class Person:-

public abstract class Person {

     // Attributes of Person Class:-
     protected String name;
     protected String gender;
     protected int age;

     // No-Arg Constructor:-
     public Person() {
          this.name = "Unknown";
          this.gender = "Unknown";
          this.age = 0;
     }

     // Parameterized Constructor #1:-
     public Person(String name) {
          this.name = name;
          this.gender = "Unknown";
          this.age = 0;
     }

     // Parameterized Constructor #2:-
     public Person(String name, String gender) {
          this.name = name;
          this.gender = gender;
          this.age = 0;
     }

     // Parameterized Constructor #3:-
     public Person(String name, String gender, int age) {
          this.name = name;
          this.gender = gender;
          this.age = age;
     }

     // Setters and Getters:-
     public void setName(String name) {
     }

     public void setGender(String gender) {
     }

     public void setAge(int age) {
     }

     public String getName() {
          return name;
     }

     public String getGender() {
          return gender;
     }

     public int getAge() {
          return age;
     }
}
// End of Person Class.
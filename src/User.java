import java.util.ArrayList;

public class User {


    private static int counter = 26000;
    protected final int id;
    protected String name;
    protected String password;
    protected String nationality;
    protected int age;
    protected float purse;
    protected ArrayList<Ticket> bookedTickets;



    public User(boolean incrementCounter) {
        if (incrementCounter) {
            this.id = ++counter;
        } else {
            this.id = counter;
        }
    }





    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getPurse() {
        return purse;
    }

    public void setPurse(float purse) {
        this.purse = purse;
    }

    public int getId() {
        return id;
    }


}

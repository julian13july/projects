/**
 * Class Customer.
 */
public class Customer {
    private static int idCounter;
    private int id;
    private String name;
    private String surname;
    private int birthDate;
    private String phoneNumber;
    private boolean active;

    public Customer(String name, String surname, int birthDate, String phoneNumber) {
        this.id = idCounter++;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getBirthDate() {
        return this.birthDate;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public int getId() {
        return this.id;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
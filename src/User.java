import javafx.beans.property.SimpleStringProperty;

public class User {

    private final SimpleStringProperty id;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    private final SimpleStringProperty city;
    private final SimpleStringProperty contact;
    private final SimpleStringProperty accountcreated;
    
    
    public User(String id, String uname, String pword, String city, String connum, String acccreated){
        this.id = new SimpleStringProperty(id);
        this.username = new SimpleStringProperty(uname);
        this.password = new SimpleStringProperty(pword);
        this.city = new SimpleStringProperty(city);
        this.contact = new SimpleStringProperty(connum);
        this.accountcreated = new SimpleStringProperty(acccreated);
    
    }

    public String getId(){
        return id.get();
    }
    public String getUsername(){
        return username.get();
    }
    public String getPassword(){
        return password.get();
    }
    public String getAccountcreated(){
        return accountcreated.get();
    }
    public String getCity(){
        return city.get();
    }
    public String getContact(){
        return contact.get();
    }

}

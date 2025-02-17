import javafx.beans.property.SimpleStringProperty;

public class User {

    private final SimpleStringProperty id;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    private final SimpleStringProperty accountcreated;
    
    
    public User(String id, String uname, String pword, String acccreated){
        this.id = new SimpleStringProperty(id);
        this.username = new SimpleStringProperty(uname);
        this.password = new SimpleStringProperty(pword);
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

}

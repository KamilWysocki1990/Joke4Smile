package j4s.com.jokeforsmile.repository;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RandomJokes {
    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }


    @PrimaryKey()
   private int id;
   private String type;
   private String setup;
   private String punchline;
    private String accountName;
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountName() {

        return accountName;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSetup() {
        return setup;
    }

    public String getPunchline() {
        return punchline;
    }





}

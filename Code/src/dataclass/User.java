package dataclass;

/**
 * Class "User" is a dataclass. The main task is saving informations. You can choose a starting value for XP.
 * the class ist controller by the controllerclass UserController.
 */


public class User {

        /* Variables*/
    private static int startXP = 100;
    private int UserID;
    private String Useremail;
    private String Username;
    private String Userpassword;
    private int Userxp;
    private int Useruploads;
    private User next;
    private User before;



    /*KOnstrukt0r*/
    public User (String startUseremail, String startUsername, String startUserpassword, int actUserID) {
        this.Useremail = startUseremail;
        this.Username = startUsername;
        this.Userpassword = startUserpassword;
        this.Useruploads = 0;
        this.UserID = actUserID;
        setUserID(getUserID()+1);
        Userxp = startXP;
    }




    /* GETS */
    public int getUserID() {
        return UserID;
    }

    public String getUseremail() {
        return Useremail;
    }

    public String getUsername() {
        return Username;
    }

    public String getUserpassword() {
        return Userpassword;
    }

    public int getUserxp() {
        return Userxp;
    }

    public int getUseruploads() {
        return Useruploads;
    }

    public User getNext() {
        return next;
    }

    public User getBefore() {
        return before;
    }

    /*SETS */
    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public void setUseremail(String Useremail) {
        this.Useremail = Useremail;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setUserpassword(String Userpassword) {
        this.Userpassword = Userpassword;
    }

    public void setUserxp(int Userxp) {
        this.Userxp = Userxp;
    }

    public void setUseruploads(int Useruploads) {
        this.Useruploads = Useruploads;
    }

    public void setNext(User next) {
        this.next = next;
    }

    public void setBefore(User before) {
        this.before = before;
    }





    /* Methods */

    public void getXP()
    {
        setUserxp(getUserxp()+1);
    }
}





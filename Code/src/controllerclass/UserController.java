
package controllerclass;


import dataclass.User;
import dataclass.picture;

import java.util.Scanner;

/**
 * Class "UserController" is a controllerclass. It controlls all processes that effects the dataclass User.
 * its a list, that saves all Users. you can create a new user, delete an old or change acutal date. It is possible to print a user to the screen.
 *
 */

public class UserController {

    /* Variables*/
    private User first;
    private User last;
    private int actualUserID = 0;



    /*KOnstrukt0r*/
    public UserController () {
        this.first = null;
        this.last = null;
    }


    /* GETS */
    public User getFirst() {
        return first;
    }

    public User getLast() {
        return last;
    }

    public int getActualUserID() {
        return actualUserID;
    }

    /* SETS */
    public void setFirst(User first) {
        this.first = first;
    }

    public void setLast(User last) {
        this.last = last;
    }

    public void setActualUserID(int actualUserID) {
        this.actualUserID = actualUserID;
    }



    /* METHODS */

    /**
     * the method creates a new User and inserts it into the list. It proofs, that the name and the mail isnt used twice.
     * A new User is set. The method gets the chosen variables and set it. The next and before references are set.
     * @param newMail User.mail
     * @param newName User.name
     * @param newPW User.PW
     * @return returns created User
     */
    public User UserCreate(String newMail, String newName, String newPW)
    {

        //proof same Name
        Boolean found = false;
        User search = getFirst();

        while(search != null)
        {
            if(search.getUsername() == newName)
                found = true;

            search=search.getNext();

        }

        if(found == true)
        {
            System.out.print("User konnte nicht erstellt werden, Name vergeben \n");
            return null;
        }


        //proof same Mail
        search = getFirst();

        while(search != null)
        {
            if(search.getUseremail() == newMail)
                found = true;

            search=search.getNext();

        }

        if(found == true)
        {
            System.out.print("User konnte nicht erstellt werden, Mail vergeben \n");
            return null;
        }

        User newUser = new User(newMail, newName, newPW, actualUserID);

        System.out.print("User mit Id: "+newUser.getUserID()+" erstellt! \n");

        if (getFirst() == null)
        {
            setLast(newUser);
            setFirst(newUser);
        }
        else
        {
            getLast().setNext(newUser);
            newUser.setBefore(getLast());
            setLast(newUser);

        }

        setActualUserID(getActualUserID()+1);
        return newUser;
    }


    /**
     * Deletes a User and removes him from list.
     * Also deletes all pictures related to that User and removes them from piclist. The references of before and next will be "repaired"
     * @param OldUser User that should be deleted
     * @param picctrl actual picController
     * @return -1 = error, 0 is good
     */
    public int UserDelete(User OldUser, picController picctrl)
    {

        if(OldUser != this.getFirst())
            OldUser.getBefore().setNext(OldUser.getNext());
        else
            setFirst(OldUser.getNext());

        if(OldUser != this.getLast())
            OldUser.getNext().setBefore(OldUser.getBefore());
        else
            setLast(OldUser.getBefore());

        if(OldUser.getUseruploads()>0) {

            picture search = picctrl.getFirst();
            while(search!=null)
            {
                if (search.getRelatedUser().getUserID() == OldUser.getUserID())
                    picctrl.picDelete(search);

                search = search.getNext();
            }
        }


        return 0;
    }


    /**
     * Changes the mail from a User. Proof if mail is used. Returns if mail is already in use
     * @param changingUser User, which mail should change
     * @param newMail new mail
     * @return -1 = error, 0 is good
     */
    public int changeEmail(User changingUser, String newMail)
    {

        User search = getFirst();
        boolean found = false;

        while(search != null)
        {
            if(search.getUseremail() == newMail)
                found = true;

            search=search.getNext();

        }

        if(found == true)
        {
            System.out.print("User konnte nicht erstellt werden, Mail vergeben \n");
            return -1;
        }

        changingUser.setUseremail(newMail);

        System.out.println("Your new Mail:" + changingUser.getUseremail()+"\n");
        return 0;
    }


    /**
     * changes the password from a User.
     * @param changingUser User, which password should change
     * @param newPW  new password
     * @return -1 = error, 0 is good
     */
    public int changePW(User changingUser, String newPW)
    {
        changingUser.setUserpassword(newPW);

        System.out.println("Your new Password:" + changingUser.getUserpassword()+"\n");
        return 0;
    }


    /**
     * prints a User and all his data to consoles
     * @param User User, that is printed
     * @return -1 = error, 0 is good
     */
    public int printUser(User User)
    {
        System.out.print("ID: " + User.getUserID() + "\n");
        System.out.print("Name: " + User.getUsername() + "\n");
        System.out.print("Mail: " + User.getUseremail() + "\n");
        System.out.print("PW: " + User.getUserpassword() + "\n");
        System.out.print("XP: " + User.getUserxp() + "\n");

        return 0;
    }
}



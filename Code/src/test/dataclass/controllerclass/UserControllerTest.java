package controllerclass;

import org.junit.Test;
import dataclass.User;
import dataclass.Picture;

import controllerclass.PicController;


import static org.junit.Assert.*;

/**
* test class for UserController
 */
public class UserControllerTest {

    /**
     * proof if working 1 User
     * @throws Exception
     */
    @Test
    public void UserCreate_1User() throws Exception {

        UserController Uctrl = new UserController();
        User testUser = Uctrl.UserCreate("test@test.de", "Max Mustermann", "1234");

        assertTrue(Uctrl.getFirst()==testUser);


    }

    /**
     * proof if working three Users
     * @throws Exception
     */
    @Test
    public void UserCreate_3User() throws Exception {

        UserController Uctrl = new UserController();
        User testUser = Uctrl.UserCreate("test@test.de", "Max Mustermann", "1234");
        User testUser2 = Uctrl.UserCreate("test@test1.de", "Peter Pan", "");
        User testUser3 = Uctrl.UserCreate("test@test2.de", "Paul Panzer", "");



        assertTrue(Uctrl.getFirst().getNext()==testUser2);

        assertTrue(Uctrl.getLast()==testUser3);



    }

    /**
     * Proof if it works, if name is used twice
     * @throws Exception
     */
    @Test
    public void UserCreate_sameName() throws Exception {

        UserController Uctrl = new UserController();
        User testUser = Uctrl.UserCreate("test@test1.de", "Max Mustermann", "1234");
        User testUser2 = Uctrl.UserCreate("test@test2.de", "Max Mueller", "1234");
        User testUser3 = Uctrl.UserCreate("test@test3.de", "Peter Mustermann", "1234");
        User testUser4 = Uctrl.UserCreate("test@test4.de", "Frank Elstner", "1234");
        User testUser5 = Uctrl.UserCreate("test@test5.de", "Max Mustermann", "1234");
        User testUser6 = Uctrl.UserCreate("test@test6.de", "Max Heinz", "1234");

    }

    /**
     * proof if it works, if mail is used twice
     * @throws Exception
     */
    @Test
    public void UserCreate_sameMail() throws Exception {

        UserController Uctrl = new UserController();

        User testUser2 = Uctrl.UserCreate("test@567.de", "Max Mueller", "1234");
        User testUser3 = Uctrl.UserCreate("test@test.de", "Peter Mustermann", "1234");
        User testUser4 = Uctrl.UserCreate("test@test.de", "Frank Elstner", "1234");


    }

    /**
     * try if last User is deleted correctly
     * @throws Exception
     */
    @Test
    public void UserDelete_3User_list() throws Exception {

        UserController Uctrl = new UserController();
        controllerclass.picController Pctrl = new picController();

        User testUser = Uctrl.UserCreate("test@test.de", "Max Mustermann", "1234");
        User testUser2 = Uctrl.UserCreate("test@test2.de", "Peter Pan", "");
        User testUser3 = Uctrl.UserCreate("test@test3.de", "Paul Panzer", "");

        Uctrl.UserDelete(testUser2, Pctrl);



        assertTrue(testUser.getNext() == testUser3 && testUser3.getBefore()==testUser);


        Uctrl.UserDelete(testUser3, Pctrl);


        assertTrue(Uctrl.getLast()==testUser);

        Uctrl.UserDelete(testUser, Pctrl);


        assertTrue(Uctrl.getLast()==null && Uctrl.getFirst()==null);



    }


    /**
     * trys if Pics are deleted too, if User is deleted
     * @throws Exception
     */
    @Test
    public void UserDeletePics() throws Exception {
        UserController Uctrl = new UserController();
        picController Pctlr = new picController();
        User testUser = Uctrl.UserCreate("test@test.de", "Max Mustermann", "1234");
        User testUser2 = Uctrl.UserCreate("test@test0.de", "Peter Pan", "");
        User testUser3 = Uctrl.UserCreate("test@test2.de", "Paul Panzer", "");


        Picture testPic = Pctlr.picCreate(testUser, "nolink");
        Picture testPic2 = Pctlr.picCreate(testUser2, "nolink");
        Picture testPic3= Pctlr.picCreate(testUser2, "nolink");
        Picture testPic4 = Pctlr.picCreate(testUser, "nolink");
        Picture testPic5 = Pctlr.picCreate(testUser3, "nolink");
        Picture testPic6 = Pctlr.picCreate(testUser, "nolink");
        Picture testPic7 = Pctlr.picCreate(testUser2, "nolink");
        Picture testPic8 = Pctlr.picCreate(testUser, "nolink");
        Picture testPic9 = Pctlr.picCreate(testUser3, "nolink");

       Uctrl.UserDelete(testUser3, Pctlr);

        assertTrue(Pctlr.getLast() != testPic9);

    }

    @Test
    public void changeEmail() throws Exception {
        UserController Uctrl = new UserController();
        User testUser = Uctrl.UserCreate("test@test.de", "Max Mustermann", "1234");
        String newMail = "new@mail.de";

        Uctrl.changeEmail(testUser, newMail);


        assertTrue(testUser.getUser_email() == newMail);


    }

    /**
     * trys if it is possilbe to chance PW
     * @throws Exception
     */
    @Test
    public void changePW() throws Exception {
        UserController Uctrl = new UserController();
        User testUser = Uctrl.UserCreate("test@test.de", "Paul Panzer", "");
        String newPW = "neuesPasswort";

        Uctrl.changePW(testUser, newPW);


        assertTrue(testUser.getUser_password() == newPW);

    }

}
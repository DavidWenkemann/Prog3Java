package controllerclass;

import dataclass.User;
import dataclass.Picture;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * testclass for picController
 */
public class PicControllerTest {

    /**
     * proof if creates 1 User and one pic is working
     * @throws Exception
     */
    @Test
    public void picCreate_1User_1Pic() throws Exception {

        UserController Uctrl = new UserController();
        picController Pctlr = new picController();
        User testUser = Uctrl.UserCreate("test@test.de", "Max Mustermann", "1234");

        Picture testPic = Pctlr.picCreate(testUser, "nolink");

        assertTrue(Pctlr.getLast() == testPic);


           }

    /**
     * proof if creating three Users with 9 oics is working
     * @throws Exception
     */
    @Test
    public void picCreate_FewUser_FewPic() throws Exception {

        UserController Uctrl = new UserController();
        picController Pctlr = new picController();
        User testUser = Uctrl.UserCreate("test@test.de", "Max Mustermann", "1234");
        User testUser2 = Uctrl.UserCreate("test@test1.de", "Peter Pan", "");
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


        assertTrue(testUser2.getUser_uploads() == 3);

        assertTrue(testUser3.getUser_xp() == 102);


    }

    /**
     * proof if Delete works, 1 User, 1 pic
     * @throws Exception
     */
    @Test
    public void picDelete() throws Exception {
        UserController Uctrl = new UserController();
        picController Pctlr = new picController();
        User testUser = Uctrl.UserCreate("test@test.de", "Max Mustermann", "1234");

        Picture testPic = Pctlr.picCreate(testUser, "nolink");

        Pctlr.picDelete(testPic);

        assertTrue(Pctlr.getLast() == null && Pctlr.getFirst() == null && testUser.getUser_uploads() == 0);


    }

    /**
     * proof if Delete works, 2 User, 9 pic
     * @throws Exception
     */
    @Test
    public void picDelete_FewUser_FewPics() throws Exception {
        UserController Uctrl = new UserController();
        picController Pctlr = new picController();
        User testUser = Uctrl.UserCreate("test@test.de", "Max Mustermann", "1234");
        User testUser2 = Uctrl.UserCreate("test@test1.de", "Peter Pan", "");
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

        Pctlr.picDelete(testPic6);


        assertTrue(testPic5.getNext()==testPic7 && testPic7.getBefore() == testPic5);


        Pctlr.picDelete(testPic9);

        assertTrue(testPic8.getNext()==null && Pctlr.getLast()== testPic8);


    }

    /**
     * Comments pic and print it to console
     * @throws Exception
     */
    @Test
    public void picComment() throws Exception {
        UserController Uctrl = new UserController();
        picController Pctlr = new picController();
        User testUser = Uctrl.UserCreate("test@test2.de", "Max Mustermann", "1234");
        User testUser2 = Uctrl.UserCreate("test@te2.de", "Peter Pan", "1234");


        Picture testPic = Pctlr.picCreate(testUser, "nolink");

        String newContent = "Dieses Bild ist schoen!";
        Pctlr.picComment(testPic, testUser, newContent);
        Pctlr.picComment(testPic, testUser2, "Was geht ab?");

        Pctlr.printComments(testPic);



        assertTrue(testPic.getPic_firstComment().getContent() == newContent);

    }


    /**
     * test if rating is correct, 3 User, 9pics
     * @throws Exception
     */
    @Test
    public void picRateWithPics() throws Exception {
        UserController Uctrl = new UserController();
        picController Pctlr = new picController();
        User testUser = Uctrl.UserCreate("test@test.de", "Max Mustermann", "1234");
        User testUser2 = Uctrl.UserCreate("test@test1.de", "Peter Pan", "");
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

        Pctlr.picRateWithPics(testUser, testPic2, testPic3);


        assertTrue(testPic2.getPic_elo()>testPic3.getPic_elo() && testPic2.getRateCount() == 1);

    }


    /**
     * creates 9 pics and return random one
     * @throws Exception
     */
    @Test
    public void  randomPic() throws Exception {
        UserController Uctrl = new UserController();
        picController Pctlr = new picController();
        User testUser = Uctrl.UserCreate("test@test.de", "Max Mustermann", "1234");
        User testUser2 = Uctrl.UserCreate("test@test1.de", "Peter Pan", "");
        User testUser3 = Uctrl.UserCreate("test@test4.de", "Paul Panzer", "");


        Picture testPic = Pctlr.picCreate(testUser, "nolink");
        Picture testPic2 = Pctlr.picCreate(testUser2, "nolink");
        Picture testPic3= Pctlr.picCreate(testUser2, "nolink");
        Picture testPic4 = Pctlr.picCreate(testUser, "nolink");
        Picture testPic5 = Pctlr.picCreate(testUser3, "nolink");
        Picture testPic6 = Pctlr.picCreate(testUser, "nolink");
        Picture testPic7 = Pctlr.picCreate(testUser2, "nolink");
        Picture testPic8 = Pctlr.picCreate(testUser, "nolink");
        Picture testPic9 = Pctlr.picCreate(testUser3, "nolink");

        Picture search = Pctlr.getFirst();
        Picture newPic = Pctlr.randomPic();

        while (search != newPic)
            search = search.getNext();

        assertTrue(true);


    }
}
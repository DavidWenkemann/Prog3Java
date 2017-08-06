package controllerclass;
import dataclass.Picture;
import dataclass.User;
import dataclass.Comment;

import java.util.Random;

/**
 * Class "picController" is a controllerclass. It controlls all processes that effects the dataclass Picture
 *  its a list, that saves all Pictures. It´ possible to create new Pics, delete some or comment them if you want to.
 *  You can also rate the picture or compare them with another.
 */
public class PicController {

        /* Variables*/
    private int actualpicID;
    private Picture first;
    private Picture last;


       /*KOnstrukt0r*/
    public PicController () {
        this.actualpicID = 0;
    }




    /* GETS */
    public int getActualpicID() {
        return actualpicID;
    }

    public Picture getFirst() {
        return first;
    }

    public Picture getLast() {
        return last;
    }

    /* SETS */
    public void setActualpicID(int actualpicID) {
        this.actualpicID = actualpicID;
    }

    public void setFirst(Picture first) {
        this.first = first;
    }

    public void setLast(Picture last) {
        this.last = last;
    }





    /* METHODS */

    /**
     * a new Picture will be created and related to the creating User. Insert into list. if it is the first picture in the list, it wil be set as the first and last.
     * If not, a pic before and after will be set.
     * @param Creator User, who creates the Picture
     * @param link link to the Picture
     * @return created Picture
     */
    public Picture picCreate(User Creator, String link){


        actualpicID = actualpicID +1;
        Picture newPic = new Picture(Creator, link, actualpicID);
        Creator.getXP();
        Creator.setUseruploads(Creator.getUseruploads()+1);

        if (getFirst() == null)
        {
            setFirst(newPic);
            setLast(newPic);
        }
        else
        {
            getLast().setNext(newPic);
            newPic.setBefore(getLast());
            setLast(newPic);
        }

        return newPic;
    }


    /**
     * removes the chosen picture from list and sets the new befire and after references.
     * @param pic pic, which should be deleted
     * @return deleted pic
     */
    public Picture picDelete(Picture pic)
    {


        pic.getRelatedUser().setUseruploads(pic.getRelatedUser().getUseruploads()-1);


        if(pic != this.getFirst())
            pic.getBefore().setNext(pic.getNext());
        else
            setFirst(pic.getNext());

        if(pic != this.getLast())
            pic.getNext().setBefore(pic.getBefore());
        else
            setLast(pic.getBefore());


        return pic;
    }


    /**
     * creates a new Comment and fills it with content the user wrote.
     * insert into Comment list. sets related Picture and User. If there is no comment before it creates a new comment list.
     * @param pic Commented pic
     * @param autor Commentator
     * @param newContent content of Comment
     * @return returns the created Comment
     */
    public Comment picComment(Picture pic, User autor, String newContent)
    {
        /*
            Wenn noch kein Kommentar vorhanden ist, wird neuer Kommentar erstellt und als FirstComment des Bildes gesetzt.
            Alternativ wird der letzte Kommentar im Bild gesucht und ihm als nächstes gesetzt. User kriegt XP
         */

        Comment newComment = new Comment(autor, newContent, pic);

        if(pic.getPicfirstComment()==null)
        {
            pic.setPicfirstComment(newComment);
            return newComment;
        }
        else
        {
            Comment search = pic.getPicfirstComment();
            while (search.getNext() != null)
            {
                search = search.getNext();
            }

            search.setNext(newComment);

            pic.getRelatedUser().getXP();
            autor.getUserxp();

            return newComment;
        }
    }


    /**
     * user gets two random pics from list and have to choose the better one.
     * actual the first one is always the better one (for testing). The better rated pic gets a plus in his elo (rating). The other pic gets a minus!
     * a counter is set where you can see how often the pic was rated before.
     * @param rater User who rates
     * @return -1 = error, 0 is good
     */
    public int picRate(User rater){
        /*
        vergleicht 2 bilder welches besser ist. das bessere gewinnt an elo. das schlechtere verliert. der User der bewertet gewinnt an XP
        der uploader des bildes gewinnt auch an XP
        */


        Picture pic1 = randomPic();
        Picture pic2 = randomPic();

        /*
                Hier werden beide bilder angezeigt und User muss sich entscheiden, welches ihm besser gefällt
         */

        System.out.print("Entscheide dich fuer Bild 1 oder Bild 2 \n");
        String rate = "1";

        if(rate == "1")
        {
            pic1.setPicelo(pic1.getPicelo()+1);
            pic2.setPicelo(pic2.getPicelo()-1);
        }
        else if(rate == "2")
        {
            pic1.setPicelo(pic1.getPicelo()-1);
            pic2.setPicelo(pic2.getPicelo()+1);
        }
        else
            return -1;

        rater.getXP();

        pic1.countRateCount();
        pic2.countRateCount();

        return 0;

    }


    /**
     * user gets two pics, which are chosen from the system, from list and have to choose the better one.
     * actual the first one is always the better one (for testing). The better rated pic gets a plus in his elo (rating). The other pic gets a minus!
     * a counter is set where you can see how often the pic was rated before.
     * @param rater User who rates
     * @param pic1 compared pic nr one
     * @param pic2 compared pic nr two
     * @return -1 = error, 0 is good
     */
    public int picRateWithPics(User rater, Picture pic1, Picture pic2){

        /*
        analog zu ratePic nur vorgegebene Bilder
         */

        System.out.print("Entscheide dich fuer Bild 1 oder Bild 2 \n");

        //Für aktuelle Testzwecke wird immer das erste Bild bewertet
        String rate = "1";

        if(rate == "1")
        {
            pic1.setPicelo(pic1.getPicelo()+1);
            pic2.setPicelo(pic2.getPicelo()-1);
            pic1.getRelatedUser().getXP();
        }
        else if(rate == "2")
        {
            pic1.setPicelo(pic1.getPicelo()-1);
            pic2.setPicelo(pic2.getPicelo()+1);
            pic2.getRelatedUser().getXP();

        }
        else
            return -1;

        rater.getXP();

        pic1.countRateCount();
        pic2.countRateCount();

        return 0;

    }


    /**
     * prints all Comments from a Picture to console
     * @param pic Picture, where Comments should printed
     * @return -1 = error, 0 is good
     */
    public int printComments(Picture pic)
    {
        Comment actualComment = pic.getPicfirstComment();

        while(actualComment != null)
        {
         System.out.print(actualComment.getAutor().getUsername() + ": " + actualComment.getContent() + "\n");
         actualComment = actualComment.getNext();
        }

        return 0;
    }

    /**
     * returns random pic from list
     * @return random pic
     */
    public Picture randomPic()
    {
        Random rn = new Random();
        int i = rn.nextInt() % getActualpicID();
        Picture search = getFirst();

        while(search.getPicID() < i)
            search = search.getNext();

         return search;
    }

}

package dataclass;

/**
 * Class "Picture" is a dataclass. The main task is saving informations.
 * the class ist controller by the controllerclass picController.
 */

public class Picture {


    /**
     * enum for category of Picture
     */
    public enum Category{
        NoCategory,
        Nature,
        Cars,
        Sport
    };


    /* Variables*/
    private int picID;
    private User relatedUser;
    private int picelo;
    private String piclink;
    private Comment picfirstComment;
    private Category piccategory;
    private Picture before;
    private Picture next;
    private int rateCount;



    /*StandardKonstruktor*/
    public Picture() {
    public Picture() {
    }


    /*erweiterter Konstruktor*/
    public Picture(User creator, String link, int id) {
        picID = id;
        relatedUser = creator;
        picelo = 100;
        piclink = link;
        piccategory = dataclass.Picture.Category.NoCategory;
        picfirstComment = null;
        rateCount = 0;
    }

    /* GETS */
    public int getPicID() {
        return picID;
    }

    public int getPicelo() {
        return picelo;
    }

    public String getPiclink() {
        return piclink;
    }

    public Comment getPicfirstComment() {
        return picfirstComment;
    }


    public Category getPiccategory() {
        return piccategory;
    }

    public Picture getBefore() {
        return before;
    }

    public Picture getNext() {
        return next;
    }

    public User getRelatedUser() {
        return relatedUser;
    }

    public int getRateCount() {
        return rateCount;
    }



    /*SETS */ //
    public void setPicID(int picID) {
        this.picID = picID;
    }

    public void setPicelo(int picelo) {
        this.picelo = picelo;
    }

    public void setPiclink(String piclink) {
        this.piclink = piclink;
    }

    public void setPicfirstComment(Comment picfirstComment) {
        this.picfirstComment = picfirstComment;
    }

    public void setPiccategory(Category piccategory) {
        this.piccategory = piccategory;
    }

    public void setBefore(Picture before) {
        this.before = before;
    }

    public void setNext(Picture next) {
        this.next = next;
    }

    public void setRelatedUser(User relatedUser) {
        this.relatedUser = relatedUser;
    }

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
    }


     /* Methods */
    public void countRateCount()
    {
        setRateCount(getRateCount()+1);
    }

}





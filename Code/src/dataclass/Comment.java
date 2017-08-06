package dataclass;

/**
 * Class "Comment" is a dataclass. The main task is saving informations. Every Comment is related to a pic and User.
 */
public class Comment {

    /* Variables*/
    private User autor;
    private String content;
    private Picture relatedPic;
    private Comment next;
    private Comment before;

    /*KOnstrukt0r*/
    public Comment (User newAutor, String newcontent, Picture newPic) {
        autor = newAutor;
        content = newcontent;
        relatedPic = newPic;
        next = null;
        before = null;
    }


    /* GETS */
    public Comment getNext() {
        return next;
    }

    public Comment getBefore() {
        return before;
    }

    public User getAutor() {
        return autor;
    }

    public String getContent() {
        return content;
    }

    public Picture getRelatedPic() {
        return relatedPic;
    }


    /* SETS */
    public void setNext(Comment next) {
        this.next = next;
    }

    public void setBefore(Comment before) {
        this.before = before;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRelatedPic(Picture relatedPic) {
        this.relatedPic = relatedPic;
    }




}

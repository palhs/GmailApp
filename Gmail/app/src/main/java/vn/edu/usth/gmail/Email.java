package vn.edu.usth.gmail;

public class Email {
    String name;
    String head_mail;
    String content;
    String date;

    int image;

    public Email(String name, String head_mail, String content, int image, String date) {
        this.name = name;
        this.head_mail = head_mail;
        this.content = content;
        this.image = image;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead_mail() {
        return head_mail;
    }

    public void setHead_mail(String head_mail) {
        this.head_mail = head_mail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

package by.kirill.pympproject.bean;

import java.io.Serializable;
import java.util.Objects;

public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idComment;
    private String content;
    private String newsTitle;
    private String author;

    public Comment() {
    }


    public Comment(int idComment, String content, String newsTitle, String author) {
        this.idComment = idComment;
        this.content = content;
        this.newsTitle = newsTitle;
        this.author = author;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return getIdComment() == comment.getIdComment() &&
                getContent().equals(comment.getContent()) &&
                getNewsTitle().equals(comment.getNewsTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdComment(), getContent(), getNewsTitle());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "idComment=" + idComment +
                ", content='" + content + '\'' +
                ", newsTitle='" + newsTitle + '\'' +
                '}';
    }
}

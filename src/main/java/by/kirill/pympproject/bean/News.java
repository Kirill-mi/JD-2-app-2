package by.kirill.pympproject.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class News implements Serializable {
    private String title;
    private String brief;
    private Date date;

    public News() {
    }

    public News(String title, String brief, Date date) {
        this.title = title;
        this.brief = brief;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(title, news.title) &&
                Objects.equals(brief, news.brief);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, brief);
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", date=" + date +
                '}';
    }
}

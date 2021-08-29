package by.kirill.pympproject.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class News implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String brief;
    private String text;
    private LocalDate date;
    private String author;

    public News() {
    }

    public News(String title, String brief, String text, LocalDate date,String author) {
        this.title = title;
        this.brief = brief;
        this.text = text;
        this.date = date;
        this.author=author;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return title.equals(news.title) &&
                brief.equals(news.brief) &&
                text.equals(news.text) &&
                date.equals(news.date) &&
                author.equals(news.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, brief, text, date, author);
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", date=" + date +
                '}';
    }


    public static class Builder {
        public Builder() {
        }

        private String title;
        private String brief;
        private String text;
        private LocalDate date;
        private String author;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setBrief(String brief) {
            this.brief = brief;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setDate(LocalDate date) {
            this.date = date;
            return this;
        }
        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }
        public News build() {
            return new News(title, brief, text, date,author);
        }
    }
}

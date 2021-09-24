package by.kirill.pympproject.dao.comment;

import by.kirill.pympproject.bean.Comment;
import by.kirill.pympproject.dao.DAOException;

import java.util.List;


public interface CommentDAO {

    void createComment(Comment comment) throws DAOException;

    List<Comment> getComments(String newsTitle) throws DAOException;
}

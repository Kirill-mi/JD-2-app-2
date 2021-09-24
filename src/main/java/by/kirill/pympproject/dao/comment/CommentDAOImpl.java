package by.kirill.pympproject.dao.comment;

import by.kirill.pympproject.bean.Comment;
import by.kirill.pympproject.dao.DAOException;
import by.kirill.pympproject.dao.connection.ConnectionPool;
import by.kirill.pympproject.dao.connection.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl implements CommentDAO {
    private final static String SQL_READ_LAST_COMMENTS = "SELECT  * FROM comment WHERE news_title=?";
    private final static String SQL_ADD_COMMENT = "INSERT INTO comment ( comment_text,news_title,author) Values (?,?,?)";


    @Override
    public void createComment(Comment comment) throws DAOException {
        String author = comment.getAuthor();
        String newsTitle = comment.getNewsTitle();
        String commentText = comment.getContent();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_COMMENT)) {
            preparedStatement.setString(1, commentText);
            preparedStatement.setString(2, newsTitle);
            preparedStatement.setString(3, author);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Comment> getComments(String newsTitle) throws DAOException {
        List<Comment> commentArrayList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_LAST_COMMENTS)) {
            preparedStatement.setString(1, newsTitle);
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    int commentId = result.getInt(1);
                    String comment = result.getString(2);
                    String newsTitleFromBase = result.getString(3);
                    String author = result.getString(4);
                    commentArrayList.add(new Comment(commentId, comment, newsTitleFromBase, author));
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
        return commentArrayList;
    }
}

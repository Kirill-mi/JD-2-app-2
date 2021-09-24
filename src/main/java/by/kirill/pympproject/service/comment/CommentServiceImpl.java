package by.kirill.pympproject.service.comment;

import by.kirill.pympproject.bean.Comment;
import by.kirill.pympproject.dao.comment.CommentDAO;
import by.kirill.pympproject.dao.DAOException;
import by.kirill.pympproject.dao.DaoProvider;
import by.kirill.pympproject.service.ServiceException;

import java.util.ArrayList;

public class CommentServiceImpl implements CommentService {

    private final static DaoProvider daoProvider = DaoProvider.getInstance();
    private final CommentDAO commentDAO = daoProvider.getCommentDAO();

    public void createComment(Comment comment) throws ServiceException {
        try {
            commentDAO.createComment(comment);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<Comment> getComments(String newsTitle) throws ServiceException {
        ArrayList<Comment> commentArrayList;
        try {
            commentArrayList = new ArrayList<>(commentDAO.getComments(newsTitle));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return commentArrayList;
    }
}

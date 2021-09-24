package by.kirill.pympproject.service.comment;

import by.kirill.pympproject.bean.Comment;
import by.kirill.pympproject.service.ServiceException;


import java.util.ArrayList;

public interface CommentService {

    void createComment(Comment comment) throws ServiceException;

    ArrayList<Comment> getComments(String newsTitle) throws ServiceException;
}

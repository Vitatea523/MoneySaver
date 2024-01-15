package com.elec5619.g20.service;

import com.elec5619.g20.pojo.Comment;
import com.elec5619.g20.vo.JsonResult;

public interface CommentService {

    JsonResult<Comment> addComment(Comment comment);

    JsonResult<Comment> deleteCommentById(Comment comment);

    JsonResult<Integer> delete();
}

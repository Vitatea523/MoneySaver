package com.elec5619.g20.service.impl;

import com.elec5619.g20.dao.CommentMapper;
import com.elec5619.g20.pojo.Comment;
import com.elec5619.g20.service.CommentService;
import com.elec5619.g20.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public JsonResult<Comment> addComment(Comment comment) {
        try {
            int result = commentMapper.addComment(comment);
            if (result == 0) {
                return JsonResult.build(400, "Insert fail!", comment);
            }else {
                return JsonResult.build(200, "Success", comment);
            }
        } catch (Exception e) {
            return JsonResult.build(400, e.getMessage(), comment);
        }
    }

    @Override
    public JsonResult<Comment> deleteCommentById(Comment comment) {
        try {
            int result = commentMapper.deleteCommentById(comment);
            if (result == 0) {
                return JsonResult.build(400, "Delete fail!", comment);
            } else {
                return JsonResult.build(200, "Success", comment);
            }
        } catch (Exception e) {
            return JsonResult.build(400, e.getMessage(), comment);
        }
    }

    @Override
    public JsonResult<Integer> delete() {
        Integer result = commentMapper.delete();
        if(result == 1) {
            return JsonResult.build(200, "Success", null);
        } else {
            return JsonResult.build(400, "Fail", null);
        }
    }
}

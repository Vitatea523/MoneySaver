package com.elec5619.g20.dao;

import com.elec5619.g20.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    int addComment(Comment comment);

    List<Comment> queryCommentByProduct(int productId);

    int deleteCommentById(Comment comment);

    int delete();
}

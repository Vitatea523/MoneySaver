package com.elec5619.g20.dao;

import com.elec5619.g20.pojo.Collect;
import com.elec5619.g20.pojo.Products;
import com.elec5619.g20.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectMapper {

    int addCollect(Collect collect);

    int deleteCollect(Collect collect);

    int delete();

    List<Products> queryCollectByUserId(User user);

    Collect queryByUserProduct(int userId, int productId);
}

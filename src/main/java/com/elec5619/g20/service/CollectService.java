package com.elec5619.g20.service;

import com.elec5619.g20.pojo.Collect;
import com.elec5619.g20.pojo.Products;
import com.elec5619.g20.pojo.User;
import com.elec5619.g20.vo.JsonResult;

import java.util.List;

public interface CollectService {

    JsonResult<Collect> addCollect(Collect collect);

    JsonResult<Collect> deleteCollect(Collect collect);

    JsonResult<List<Products>> queryCollectByUserId(User user);

    JsonResult<Integer> delete();
}

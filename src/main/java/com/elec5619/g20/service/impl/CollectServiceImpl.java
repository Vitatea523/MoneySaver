package com.elec5619.g20.service.impl;

import com.elec5619.g20.dao.CollectMapper;
import com.elec5619.g20.pojo.Collect;
import com.elec5619.g20.pojo.Products;
import com.elec5619.g20.pojo.User;
import com.elec5619.g20.service.CollectService;
import com.elec5619.g20.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public JsonResult<Collect> addCollect(Collect collect) {
        try {
            int result = collectMapper.addCollect(collect);
            if (result == 0) {
                return JsonResult.build(400, "Collect fail!", collect);
            }else {
                return JsonResult.build(200, "Success", collect);
            }
        } catch (Exception e) {
            return JsonResult.build(400, e.getMessage(), collect);
        }
    }

    @Override
    public JsonResult<Collect> deleteCollect(Collect collect) {
        try {
            int result = collectMapper.deleteCollect(collect);
            if (result == 0) {
                return JsonResult.build(400, "Delete fail!", collect);
            }else {
                return JsonResult.build(200, "Success", collect);
            }
        } catch (Exception e) {
            return JsonResult.build(400, e.getMessage(), collect);
        }
    }

    @Override
    public JsonResult<List<Products>> queryCollectByUserId(User user) {
        try {
            List<Products> products = collectMapper.queryCollectByUserId(user);
            return JsonResult.build(200, "Success", products);

        } catch (Exception e) {
            return JsonResult.build(400, e.getMessage(), null);
        }
    }

    @Override
    public JsonResult<Integer> delete() {
        Integer result = collectMapper.delete();
        if(result == 1) {
            return JsonResult.build(200, "Success", null);
        } else {
            return JsonResult.build(400, "Fail", null);
        }
    }
}

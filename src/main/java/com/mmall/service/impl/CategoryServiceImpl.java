package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse addCategory(Integer parentId, String categoryName) {
        if (parentId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMsg("增加品类的参数错误");
        }
        Category category = new Category();
        category.setParentId(parentId);
        category.setName(categoryName);
        category.setStatus(true);
        int res = categoryMapper.insert(category);
        if (res > 0) {
            return ServerResponse.createBySuccessMsg("添加品类成功");
        }
        return ServerResponse.createByErrorMsg("添加品类失败");
    }

    @Override
    public ServerResponse setCategoryName(Integer categoryId, String categoryName) {
        if (categoryId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMsg("修改品类的参数错误");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        int res = categoryMapper.updateByPrimaryKeySelective(category);
        if (res > 0) {
            return ServerResponse.createBySuccessMsg("修改品类名成功");
        }
        return ServerResponse.createByErrorMsg("修改品类名失败");
    }

    @Override
    public ServerResponse selectByCategoryId(int categoryId) {
        List<Category> categories = categoryMapper.selectByParentId(categoryId);
        if (CollectionUtils.isNotEmpty(categories)) {
            return ServerResponse.createBySuccess(categories);
        }
        return ServerResponse.createByErrorMsg("未找到该品类");
    }

    @Override
    public ServerResponse getDeepCategory(Integer categoryId) {
        if (categoryId == null) {
            return ServerResponse.createByErrorMsg("搜索品类的参数错误");
        }
        Set<Category> categories = Sets.newHashSet();
        findByIdAndParantId(categories, categoryId);
        List<Integer> categoryIdList = Lists.newArrayList();
        for (Category categoryItem: categories) {
            categoryIdList.add(categoryItem.getId());
        }
        if (CollectionUtils.isEmpty(categoryIdList)) {
            return ServerResponse.createBySuccess(categoryIdList);
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    public Set<Category> findByIdAndParantId(Set<Category> categorySet, Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            categorySet.add(category);
        }
        List<Category> categories = categoryMapper.selectByParentId(categoryId);
        for (Category categoryItem: categories) {
            findByIdAndParantId(categorySet, categoryItem.getId());
        }
        return categorySet;
    }




















































}

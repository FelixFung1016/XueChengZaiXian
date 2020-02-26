package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    @Autowired
    CmsPageRepository cmsPageRepository;

    public QueryResponseResult findList(int page,int size, QueryPageRequest queryPageRequest){
        //规范传来的页码数据
        if(page <= 0){
            page = 1;
        }
        if(size <= 0){
            size = 10;
        }
        page = page - 1;
        //创建QueryResult
        QueryResult<CmsPage> queryResult = new QueryResult<>();
        //定义页码查询信息类:Pageable
        Pageable pageable = PageRequest.of(page,size);
        //调用dao接口默认方法findAll查询分页后的信息
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        //从返回的Page类中得到内容和总记录数并添加到QueryResult中
        queryResult.setList(all.getContent());
        queryResult.setTotal(all.getTotalElements());
        //定义好响应信息并放入queryResult
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }
}

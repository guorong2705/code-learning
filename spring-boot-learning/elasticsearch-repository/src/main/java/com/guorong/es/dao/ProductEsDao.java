package com.guorong.es.dao;

import com.guorong.es.entity.ProductEsModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author guorong
 * @date 2021-04-14
 */
@Repository
public interface ProductEsDao extends ElasticsearchRepository<ProductEsModel,Long> {


}

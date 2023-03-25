package com.betterjavacode.elasticsearchdemo.repositories;

import com.betterjavacode.elasticsearchdemo.models.LogData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.List;

@Repository
public interface LogDataRepository extends ElasticsearchRepository<LogData, String>
{
    List<LogData> findByHost(String host);

    List<LogData> findByMessageContaining(String message, Sort orderby);
}

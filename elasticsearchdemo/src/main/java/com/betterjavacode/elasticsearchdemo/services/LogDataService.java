package com.betterjavacode.elasticsearchdemo.services;

import com.betterjavacode.elasticsearchdemo.models.LogData;
import com.betterjavacode.elasticsearchdemo.repositories.LogDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;

@Service
public class LogDataService
{
    @Autowired
    private LogDataRepository logDataRepository;

    public LogData createLogDataIndex(final LogData logData)
    {
        return logDataRepository.save(logData);
    }

    public Iterable<LogData> createLogDataIndices(final List<LogData> logDataList)
    {
        return logDataRepository.saveAll(logDataList);
    }

    public List<LogData> getAllLogDataForHost (String host)
    {
        return logDataRepository.findByHost(host);
    }

    public LogData findById (String id)
    {
        return logDataRepository.findById(id).get();
    }

    public List<LogData> findBySearchTerm (String term, String orderby, String sort)
    {
        System.out.println("=== sort === ");
        System.out.println(sort);
        Sort.Direction dir = ((sort.equals("asc")) ? Sort.Direction.ASC : Sort.Direction.DESC);
        System.out.println(dir);

        return logDataRepository.findByMessageContaining(term, Sort.by(dir, orderby));
    }
}

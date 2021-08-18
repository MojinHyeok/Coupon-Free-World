package com.ssafy.backend.record.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.backend.record.model.RecordModel;

@Repository
public interface RecordMapper {

	int registRecord(RecordModel model)throws Exception;
	
	List<RecordModel> fetchRecord(String sourceID)throws Exception;
	
	int deleteRecord(RecordModel model)throws Exception;

}

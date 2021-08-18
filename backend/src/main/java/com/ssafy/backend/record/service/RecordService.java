package com.ssafy.backend.record.service;

import java.util.List;

import com.ssafy.backend.record.model.RecordModel;

public interface RecordService {
	
	int registRecord(RecordModel model) throws Exception;
	
	List<RecordModel> fetchRecord(String sourceID)throws Exception;
	
	int deleteRecord(RecordModel model) throws Exception;
	
	RecordModel selectRecord(RecordModel model)throws Exception;

}

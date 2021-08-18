package com.ssafy.backend.record.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.backend.record.mapper.RecordMapper;
import com.ssafy.backend.record.model.RecordModel;

@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	RecordMapper mapper;
	
	@Override
	public int registRecord(RecordModel model) throws Exception {
		return mapper.registRecord(model);
	}

	@Override
	public List<RecordModel> fetchRecord(String sourceID) throws Exception {
		return mapper.fetchRecord(sourceID);
	}

	@Override
	public int deleteRecord(RecordModel model) throws Exception {
		return mapper.deleteRecord(model);
	}

	@Override
	public RecordModel selectRecord(RecordModel model) throws Exception {
		return mapper.selectRecord(model);
	}

}

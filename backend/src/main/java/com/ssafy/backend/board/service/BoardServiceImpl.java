package com.ssafy.backend.board.service;

import com.ssafy.backend.board.mapper.BoardMapper;
import com.ssafy.backend.board.model.BoardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    public BoardMapper mapper;

    @Override
    public int writeBoard(BoardModel boardDto) throws Exception {
        return mapper.writeBoard(boardDto);
    }

    @Override
    public List<BoardModel> showList() throws Exception {
        return mapper.showList();
    }

    @Override
    public int deleteBoard(int boardNum) throws Exception {
        return mapper.deleteBoard(boardNum);
    }

    @Override
    public BoardModel selectBoard(int boardNum) throws Exception {
        return mapper.selectBoard(boardNum);
    }

    @Override
    public List<BoardModel> searchBoard(String searchOption, String value, int limit, int offset) throws Exception {
        return mapper.searchBoard(searchOption, value, limit, offset);
    }

    @Override
    public int updateBoard(BoardModel boardDto) throws Exception {
        return mapper.updateBoard(boardDto);
    }

    @Override
    public int getTotalCount() throws Exception {
        return mapper.getTotalCount();
    }

    @Override
    public List<BoardModel> selectBoardLimitOffset(int limit, int offset) throws Exception {
        return mapper.selectBoardLimitOffset(limit, offset);
    }
}

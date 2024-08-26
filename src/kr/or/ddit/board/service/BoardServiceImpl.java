package kr.or.ddit.board.service;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.vo.BoardVo;

import java.util.Collections;
import java.util.List;

public class BoardServiceImpl implements IBoardService{

    private IBoardDao dao;
    private static BoardServiceImpl service;

    private BoardServiceImpl() {
        dao = BoardDaoImpl.getInstance();
    }

    public static BoardServiceImpl getInstance() {
        if (service == null) {
            service = new BoardServiceImpl();

        }
        return service;
    }

    @Override
    public int createBoard(BoardVo boardVo) {
        return dao.createBoard(boardVo);
    }

    @Override
    public int updateBoard(BoardVo boardVo) {
        return dao.updateBoard(boardVo);
    }

    @Override
    public int deleteBoard(int input) {
        return dao.deleteBoard(input);
    }

    @Override
    public List<BoardVo> searchBoard(String str) {
        return dao.searchBoard(str);
    }

    @Override
    public int getBoardIdCount(String boardWriter) {
        return dao.getBoardIdCount(boardWriter);
    }

    @Override
    public List<BoardVo> searchBoardOne(BoardVo boardVo) {
        return Collections.emptyList();
    }

    @Override
    public List<BoardVo> displayBoard(int input) {
        return dao.displayBoard(input);
    }

    @Override
    public List<BoardVo> displayAllBoard() {
        return dao.displayAllBoard();
    }

    @Override
    public int incrementCount(int input) {
        return dao.incrementCount(input);
    }
}

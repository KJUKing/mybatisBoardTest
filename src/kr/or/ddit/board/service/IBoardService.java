package kr.or.ddit.board.service;

import kr.or.ddit.vo.BoardVo;

import java.util.List;

public interface IBoardService {

    public int createBoard(BoardVo boardVo);

    public int updateBoard(BoardVo boardVo);

    public int deleteBoard(int input);

    public List<BoardVo> searchBoard(String str);

    public int getBoardIdCount(String boardWriter);

    public List<BoardVo> searchBoardOne(BoardVo boardVo);

    public List<BoardVo> displayBoard(int input);

    public List<BoardVo> displayAllBoard();

    public int incrementCount(int input);
}

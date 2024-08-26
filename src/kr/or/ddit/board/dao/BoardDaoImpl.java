package kr.or.ddit.board.dao;

import kr.or.ddit.util.DBUtil3;
import kr.or.ddit.vo.BoardVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardDaoImpl implements IBoardDao{

    private static BoardDaoImpl dao;
    private BoardDaoImpl() {

    }

    public static BoardDaoImpl getInstance() {
        if (dao == null) {
            dao = new BoardDaoImpl();
        }
        return dao;
    }


    @Override
    public int createBoard(BoardVo boardVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int cnt = 0;

        try {
            conn = DBUtil3.getConnection();

            String sql = "INSERT INTO JDBC_BOARD " +
                    "(board_no, board_title, board_writer, board_date, board_cnt, board_content) " +
                    "VALUES (board_seq.NEXTVAL, ?, ?, SYSDATE, 0, ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, boardVo.getBoard_title());
            pstmt.setString(2, boardVo.getBoard_writer());
            pstmt.setString(3, boardVo.getBoard_content());
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException e) {
            };
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
            };
        }
        return cnt;
    }

    @Override
    public int updateBoard(BoardVo boardVo) {

        Connection  conn = null;
        PreparedStatement pstmt = null;
        int cnt = 0;

        try {
            conn = DBUtil3.getConnection();
            String sql = "update JDBC_BOARD set BOARD_TITLE = ?, " +
                    " BOARD_CONTENT = ? " +
                    "where BOARD_NO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, boardVo.getBoard_title());
            pstmt.setString(2, boardVo.getBoard_writer());
            pstmt.setInt(3, boardVo.getBoard_no());

            cnt = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException e) {
            };
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
            };
        }

        return cnt;
    }

    @Override
    public int deleteBoard(int input) {
        Connection  conn = null;
        PreparedStatement pstmt = null;
        int cnt = 0;

        try {
            conn = DBUtil3.getConnection();
            String sql = "delete from JDBC_BOARD where BOARD_NO = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, input);

            cnt = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException e) {
            };
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
            };
        }

        return cnt;
    }

    @Override
    public List<BoardVo> searchBoard(String str) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BoardVo> boardList = null;

        try {
            conn = DBUtil3.getConnection();
            String sql = "SELECT * FROM JDBC_BOARD WHERE board_title LIKE ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + str + "%");

            rs = pstmt.executeQuery();
            boardList = new ArrayList<>();
            while (rs.next()) {
                BoardVo boardVo = new BoardVo();
                boardVo.setBoard_no(rs.getInt("board_no"));
                boardVo.setBoard_title(rs.getString("board_title"));
                boardVo.setBoard_writer(rs.getString("board_writer"));
                boardVo.setBoard_cnt(rs.getInt("board_cnt"));
                boardList.add(boardVo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
            }
            ;
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException e) {
            }
            ;
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
            }
            ;
        }
        return boardList;
    }

    @Override
    public int getBoardIdCount(String boardWriter) {
        return 0;
    }

    @Override
    public List<BoardVo> searchBoardOne(BoardVo boardVo) {
        return Collections.emptyList();
    }

    @Override
    public List<BoardVo> displayBoard(int input) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BoardVo> boardList = null;

        try {
            conn = DBUtil3.getConnection();
            String sql = "SELECT * FROM JDBC_BOARD WHERE board_no = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, input);

            rs = pstmt.executeQuery();
            boardList = new ArrayList<>();
            while (rs.next()) {
                BoardVo boardVo = new BoardVo();
                //boardVo.setBoard_no(rs.getInt("board_no"));
                boardVo.setBoard_title(rs.getString("board_title"));
                boardVo.setBoard_writer(rs.getString("board_writer"));
                boardVo.setBoard_date(rs.getString("board_date"));
                boardVo.setBoard_cnt(rs.getInt("board_cnt"));
                boardVo.setBoard_content(rs.getString("board_content"));
                boardList.add(boardVo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
            }
            ;
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException e) {
            }
            ;
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
            }
            ;
        }
        return boardList;
    }

    @Override
    public List<BoardVo> displayAllBoard() {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BoardVo> boardList = null;

        try {
            conn = DBUtil3.getConnection();
            String sql = "SELECT * FROM JDBC_BOARD";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            boardList = new ArrayList<>();
            while (rs.next()) {
                BoardVo boardVo = new BoardVo();
                boardVo.setBoard_no(rs.getInt("board_no"));
                boardVo.setBoard_title(rs.getString("board_title"));
                boardVo.setBoard_writer(rs.getString("board_writer"));
                boardVo.setBoard_date(rs.getString("board_date"));
                boardVo.setBoard_cnt(rs.getInt("board_cnt"));
                boardVo.setBoard_content(rs.getString("board_content"));
                boardList.add(boardVo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
            }
            ;
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException e) {
            }
            ;
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
            }
            ;
        }
        return boardList;
    }

    @Override
    public int incrementCount(int input) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int cnt = 0;
        try {
            conn = DBUtil3.getConnection();
            String sql = "UPDATE JDBC_BOARD SET board_cnt = board_cnt + 1 WHERE board_no = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, input);

            cnt = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
            if (conn != null) try { conn.close(); } catch (SQLException e) { }
        }
        return cnt;
    }
}

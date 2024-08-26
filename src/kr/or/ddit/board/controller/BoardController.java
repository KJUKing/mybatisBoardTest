package kr.or.ddit.board.controller;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVo;

import java.util.List;
import java.util.Scanner;

public class BoardController {
    private IBoardService service;
    private Scanner scan;

    public BoardController() {
        scan = new Scanner(System.in);
        service = BoardServiceImpl.getInstance();
    }

    public static void main(String[] args) {
        new BoardController().startBoard();
    }

    private int displayMenu() {
        displayAllBoard();
        System.out.println("메뉴 : 1. 새글작성 2. 게시글보기 3. 검색 0. 작업끝");
        System.out.print("작업 선택 : ");
        return scan.nextInt();
    }

    private void displayAllBoard() {
        List<BoardVo> boardVos = service.displayAllBoard();
        System.out.println("-------------------------------------------");
        System.out.println("No         제 목        작성자         조회수");
        System.out.println("-------------------------------------------");
        for (BoardVo boardVo : boardVos) {
            System.out.println(boardVo.getBoard_no()+ "    \t"+boardVo.getBoard_title()
                    + "    \t"+boardVo.getBoard_writer()+ "    \t"+ boardVo.getBoard_cnt());
        }
    }

    private void startBoard() {
        while (true) {
            int choice = displayMenu();
            switch (choice) {
                case 1:
                    createBoard();
                    break;
                case 2:
                    displayBoard();
                    break;
                case 3:
                    searchBoard();
                    break;
                case 0:
                    System.out.println("작업을 마칩니다");
                    return;
                default:
                    System.out.println("잘못입력했습니다");
            }
        }
    }

    private void searchBoard() {
        System.out.println();
        System.out.println("검색 작업");
        System.out.println("----------------------");

        scan.nextLine();
        System.out.print("- 검색할 제목 입력 : ");
        String searchTitle = scan.nextLine().trim();

        if (searchTitle.equals("")) {
            displayAllBoard();
            System.out.println("공백입력으로 전체검색후 메인화면으로 이동합니다");
            return;
        }
        List<BoardVo> boardVos = service.searchBoard(searchTitle);
        System.out.println("-------------------------------------------");
        System.out.println("No         제 목        작성자         조회수");
        System.out.println("-------------------------------------------");
        for (BoardVo boardVo : boardVos) {
            System.out.println(boardVo.getBoard_no()+ "    \t"+boardVo.getBoard_title()
                    + "    \t"+boardVo.getBoard_writer()+ "    \t"+ boardVo.getBoard_cnt());
        }
    }

    private void displayBoard() {
        System.out.println();
        System.out.print("보기를 원하는 게시물 번호 입력 : ");
        int input = scan.nextInt();

        service.incrementCount(input);
        List<BoardVo> boardVos = service.displayBoard(input);
        System.out.println(input +"번글 내용");
        System.out.println("----------------------");
        for (BoardVo boardVo : boardVos) {
            System.out.println("- 제 목 : " + boardVo.getBoard_title());
            System.out.println("- 작성자 : " + boardVo.getBoard_writer());
            System.out.println("- 작성자 : " + boardVo.getBoard_content());
            System.out.println("- 작성일 : " + boardVo.getBoard_date());
            System.out.println("- 조회수 : " + boardVo.getBoard_cnt());
        }



        System.out.println("메뉴 : 1.수정    2.삭제    3. 리스트로 가기");
        int menu = scan.nextInt();
        switch (menu) {
            case 1: updateBoard(input);
                break;
            case 2: deleteBorad(input);
                break;
            case 3: startBoard();
                break;
            default:
                System.out.println("잘못 입력하셨습니다");
                break;
        }

    }

    private void deleteBorad(int input) {
        System.out.println();

        int cnt = service.deleteBoard(input);
        if (cnt > 0) {
            System.out.println(input+"번글이 삭제됐습니다");
        } else {
            System.out.println("삭제 실패");
        }
    }

    private void updateBoard(int input) {
        System.out.println();
        System.out.println("수정 작업하기");

        scan.nextLine();
        System.out.print("- 제 목 : ");
        String newTitle = scan.nextLine();

        System.out.print("- 내 용 : ");
        String newContent = scan.nextLine();
        System.out.println();

        BoardVo boardVo = new BoardVo();
        boardVo.setBoard_title(newTitle);
        boardVo.setBoard_writer(newContent);
        boardVo.setBoard_no(input);

        int result = service.updateBoard(boardVo);
        if (result > 0) {
            System.out.println(input+"번글이 수정됐습니다");
        } else {
            System.out.println("수정 실패");
        }

    }

    private void createBoard() {
        System.out.println();
        System.out.println("새글 작성하기");
        System.out.println("----------------------");

        System.out.print("- 제 목 : ");
        String title = scan.next();
        scan.nextLine();

        System.out.println();
        System.out.print("- 작성자 : ");
        String writer = scan.nextLine();
        System.out.println();


        System.out.print("- 내 용 : ");
        String content = scan.nextLine();


        BoardVo boardVo = new BoardVo();
        boardVo.setBoard_title(title);
        boardVo.setBoard_writer(writer);
        boardVo.setBoard_content(content);

        int cnt = service.createBoard(boardVo);
        if (cnt > 0) {
            System.out.println("글 작성 성공");
        } else {
            System.out.println("글 작성 실패");
        }

    }
}



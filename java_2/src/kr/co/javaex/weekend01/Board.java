package kr.co.javaex.weekend01;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Board {
    static int postNo = 0;

    public static void main(String[] args) {
        Board boardManager = new Board();
        ArrayList<Post> board = new ArrayList<>();
        ArrayList<Post> restoredBoard = restoreBoardFromFile("board.ser");
        if (restoredBoard != null) {
            for (Post post : restoredBoard) {
                board.add(post);
            }
        }

        boolean keep = true;
        int detailNum = -1;
        while (keep) {
            if (boardManager.getMenuStatus() == 0) {
                int menu = boardManager.selectMainMenu();
                keep = boardManager.funcMainMenu(menu, boardManager, keep, board);
            } else if (boardManager.getMenuStatus() == 1) {
                int menu = boardManager.selectListUpMenu();
                detailNum = boardManager.funcListUpMenu(menu, boardManager, keep, board);
            } else {
                int menu = boardManager.selectDetailMenu();
                boardManager.funcDetailMenu(menu, boardManager, keep, board, detailNum);
            }
        }
    }

    public int getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(int menuStatus) {
        this.menuStatus = menuStatus;
    }

    int menuStatus = 0; // 0은 메인 메뉴, 1은 목록 조회 메뉴, 2는 상세 조회 메뉴


    public int selectMainMenu() {
        System.out.println("------------------------------------------------");
        System.out.println("1.목록 조회, 2.종료");
        System.out.print("선택>");
        return numInputValid();
    }


    public int selectListUpMenu() {
        System.out.println("------------------------------------------------");
        System.out.println("1.글 작성, 2.상세 조회, 3.다중 삭제, 4.메인 메뉴 이동");
        System.out.print("선택>");
        return numInputValid();
    }


    public int selectDetailMenu() {
        System.out.println("------------------------------------------------");
        System.out.println("1.글 수정, 2.글 삭제, 3.메인 메뉴 이동");
        System.out.print("선택>");
        return numInputValid();
    }

    public boolean funcMainMenu(int menu, Board boardManager, boolean keep, ArrayList<Post> board) {
        switch (menu) {
            case 1:
                showPostList(board);
                boardManager.setMenuStatus(1);
                break;
            case 2:
                System.out.println("프로그램 종료");
                keep = false;
                saveBoardToFile(board, "board.ser");
                break;
            default:
                System.out.println("1~3 의 값을 입력하세요");
        }
        return keep;
    }

    public void showPostList(ArrayList<Post> board) {
        if (board.isEmpty()) {
            System.out.println("게시글이 없습니다.");
        } else {
            System.out.println("게시글 목록");
            System.out.println("------------------------------------------------");
            for (Post post : board) {
                System.out.println(post.listToString());
            }
        }
    }

    public int funcListUpMenu(int menu, Board boardManager, boolean keep, ArrayList<Post> board) {
        int detailNum = -1;
        switch (menu) {
            case 1:
                createPost(board);
                saveBoardToFile(board, "board.ser");
                break;
            case 2:
                int doNext = checkPostDetail(board);
                if (doNext != -1) {
                    boardManager.setMenuStatus(2);
                    detailNum = doNext;
                }
                break;
            case 3:
                multiDelete(board);
                saveBoardToFile(board, "board.ser");
                break;
            case 4:
                boardManager.setMenuStatus(0);
                System.out.println("메인 메뉴로 이동합니다.");
                break;
            default:
                System.out.println("1~4 의 값을 입력하세요");
        }
        return detailNum;
    }

    public int checkPostDetail(ArrayList<Post> board) {
        System.out.println("------------------------------------------------");
        System.out.print("상세 조회할 게시글의 식별번호를 입력하세요.>");
        int checkNum = numInputValid();
        boolean searchFail = true;
        for (Post post : board) {
            if (post.getPostNum() == checkNum) {
                searchFail = false;
                if (post.isSecret()) {
                    System.out.print("비밀 번호를 입력하세요.>");
                    Scanner passwordScanner = new Scanner(System.in);
                    if (passwordScanner.nextLine().equals(post.getPassword())) {
                        System.out.println("------------------------------------------------");
                        System.out.println(post.detailToString());
                        break;
                    } else {
                        System.out.println("비밀 번호가 틀렸습니다.");
                    }
                } else {
                    System.out.println("------------------------------------------------");
                    System.out.println(post.detailToString());
                }

            }
        }
        if (searchFail) {
            System.out.println("------------------------------------------------");
            System.out.println("해당 번호의 게시글이 존재하지 않습니다.");
            return -1;
        }
        return checkNum;

    }

    public void multiDelete(ArrayList<Post> board) {

        String[] deleteList = null;
        while (true) {
            try {
                System.out.println("------------------------------------------------");
                System.out.print("삭제할 게시글의 식별번호를 공백 기준으로 작성하세요.>");
                Scanner multiDeleteScanner1 = new Scanner(System.in);
                deleteList = multiDeleteScanner1.nextLine().split("\\s+");
                for (String tryDelete : deleteList) {
                    int tryNum = Integer.parseInt(tryDelete);
                }
                break;
            } catch (Exception e) {
                System.out.println("숫자가 아닌 입력이 존재합니다. 숫자만 입력해주세요.");
            }
        }

        for (String tryDelete : deleteList) {
            int tryNum = Integer.parseInt(tryDelete);
            boolean canDelete = false;
            boolean deleteComplete = false;
            Post target = null;
            for (Post post : board) {
                if (post.getPostNum() == tryNum) {
                    canDelete = true;
                    if (post.isSecret()) {
                        System.out.print(post.getPostNum() + "에 해당하는 비밀 번호를 입력하세요.>");
                        Scanner multiDeleteScanner2 = new Scanner(System.in);
                        if (multiDeleteScanner2.nextLine().equals(post.getPassword())) {
                            System.out.println("------------------------------------------------");
                            deleteComplete = true;
                            target = post;
                            break;
                        } else {
                            System.out.println("비밀 번호가 틀렸습니다.");
                        }
                    } else {
                        target = post;
                        deleteComplete = true;
                        break;
                    }
                }
            }
            if (!canDelete)
                System.out.println(tryNum + "에 해당하는 게시글이 존재하지 않습니다.");
            if (deleteComplete && canDelete) {
                System.out.println(target.getPostNum() + "번 게시글이 삭제되었습니다.");
                board.remove(target);
            }
        }
    }

    public void createPost(ArrayList<Post> board) {

        if (board != null) {
            for (Post post : board) {
                if (post.getPostNum() > postNo)
                    postNo = post.getPostNum();
            }
        }

        Scanner createScanner = new Scanner(System.in);
        Post newPost = new Post();
        newPost.setPostNum(++postNo);

        System.out.print("제목: ");
        newPost.setTitle(createScanner.nextLine());
        System.out.print("작성자: ");
        newPost.setWriter(createScanner.nextLine());
        System.out.print("내용: ");
        newPost.setContent(createScanner.nextLine());
        System.out.print("비밀 글 여부, y/n 입력: ");
        if (createScanner.nextLine().equals("y")) {
            newPost.setSecret(true);
            System.out.print("비밀번호: ");
            newPost.setPassword(createScanner.nextLine());
        } else {
            newPost.setSecret(false);
        }
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String formattedDate = currentDate.format(formatter);
        newPost.setDate(formattedDate);
        board.add(newPost);
    }

    public void funcDetailMenu(int menu, Board boardManager, boolean keep, ArrayList<Post> board, int detailNum) {
        switch (menu) {
            case 1:
                editPost(board, detailNum);
                saveBoardToFile(board, "board.ser");
                break;
            case 2:
                deletePost(board, detailNum);
                saveBoardToFile(board, "board.ser");
                break;
            case 3:
                boardManager.setMenuStatus(0);
                System.out.println("메인 메뉴로 이동합니다.");
                break;
            default:
                System.out.println("1~3 의 값을 입력하세요");
        }
        return;
    }

    public void editPost(ArrayList<Post> board, int detailNum) {
        Scanner editScanner = new Scanner(System.in);
        boolean editFail = true;
        for (Post post : board) {
            if (post.getPostNum() == detailNum) {
                if (post.isSecret()) {
                    System.out.print("비밀 번호를 입력하세요.>");
                    if (editScanner.nextLine().equals(post.getPassword())) {
                        System.out.print(detailNum + "번 게시글의 새로운 제목을 입력하세요.>");
                        post.setTitle(editScanner.nextLine());
                        System.out.print(detailNum + "번 게시글의 새로운 내용을 입력하세요.>");
                        post.setContent(editScanner.nextLine());
                        System.out.println(detailNum + "번 게시글의 제목과 내용이 수정되었습니다.");
                        editFail = false;
                    } else {
                        System.out.println("비밀번호 오류입니다.");
                    }
                }
                break;
            }
        }
        if (editFail) {
            System.out.println("게시글 수정에 실패하였습니다.");
        }
    }

    public void deletePost(ArrayList<Post> board, int detailNum) {
        Scanner deleteScanner = new Scanner(System.in);
        boolean deleteFail = true;
        Post target = null;
        for (Post post : board) {
            if (post.getPostNum() == detailNum) {
                if (post.isSecret()) {
                    System.out.print("비밀 번호를 입력하세요.>");
                    if (deleteScanner.nextLine().equals(post.getPassword())) {
                        target = post;
                        deleteFail = false;
                    } else {
                        System.out.println("비밀번호 오류입니다.");
                    }
                }
                else {
                    target = post;
                    deleteFail = false;
                }

                break;
            }
        }
        if (target != null) {
            board.remove(target);
            System.out.println(detailNum + "번 게시글을 삭제하였습니다.");
        } else {
            System.out.println("게시글 삭제에 실패하였스니다.");
        }
    }

    static public int numInputValid() {
        Scanner mainMenuScanner = new Scanner(System.in);
        int input;
        while (true) {
            try {
                input = mainMenuScanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                mainMenuScanner.next();
            }
        }
        return input;
    }

    public static void saveBoardToFile(ArrayList<Post> board, String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(board);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Post> restoreBoardFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (ArrayList<Post>) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}

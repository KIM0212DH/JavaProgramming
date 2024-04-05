package kr.co.javaex.weekend02;

import kr.co.javaex.weekend01.Board;
import kr.co.javaex.weekend01.Post;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class TbEx {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        TbEx tbManager = new TbEx();
        Connection conn = tbManager.makeConnect();

        boolean keep = true;
        int detailNum = -1;
        while (keep) {
            if (tbManager.getMenuStatus() == 0) {
                int menu = tbManager.selectMainMenu();
                keep = tbManager.funcMainMenu(menu, tbManager, keep, conn);
            } else if (tbManager.getMenuStatus() == 1) {
                int menu = tbManager.selectListUpMenu();
                detailNum = tbManager.funcListUpMenu(menu, tbManager, keep, conn);
            } else {
                int menu = tbManager.selectDetailMenu();
                tbManager.funcDetailMenu(menu, tbManager, keep, conn, detailNum);
            }
        }

    }

    public static Connection makeConnect() throws ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");

            String dbUrl = "jdbc:oracle:thin:@(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.ap-seoul-1.oraclecloud.com))(connect_data=(service_name=g56e711c2a2b221_dinkdb_medium.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))";

            String dbUser = "DA2401";
            String dbPassword = "Data2401";

            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                return conn;
            } else {
                return null;
            }
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

    int menuStatus = 0;

    public int getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(int menuStatus) {
        this.menuStatus = menuStatus;
    }

    public int selectMainMenu() {
        System.out.println("------------------------------------------------");
        System.out.println("1.목록 조회, 2.종료");
        System.out.print("선택>");
        return numInputValid();
    }

    public void showBoardList(Connection conn) {
        String showBoardSql = "select nb_board, nm_title, nm_content, nm_writer, da_write, cn_hit from TB_BOARD order by nb_board";
        try {
            PreparedStatement pstmt = conn.prepareStatement(showBoardSql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("-----------------------------");
                System.out.println("번호: " + rs.getInt(1));
                System.out.println("제목: " + rs.getString(2));
                System.out.println("내용: " + rs.getString(3));
                System.out.println("작성자: " + rs.getString(4));
                System.out.println("작성일자: " + rs.getString(5));
                System.out.println("조회수: " + rs.getInt(6));
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("pstmt 선언 오류");
            e.printStackTrace();
        }

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

    public boolean funcMainMenu(int menu, TbEx TbManager, boolean keep, Connection conn) {
        switch (menu) {
            case 1:
                //데이터베이스에서 가져와서 목록 보여주기
                showBoardList(conn);
                TbManager.setMenuStatus(1);
                break;
            case 2:
                System.out.println("프로그램 종료");
                keep = false;
                break;
            default:
                System.out.println("1~3 의 값을 입력하세요");
        }
        return keep;
    }

    public int funcListUpMenu(int menu, TbEx TbManager, boolean keep, Connection conn) {
        int detailNum = -1;
        switch (menu) {
            case 1:
                //createPost(board);
                createContents(conn);
                //글 작성: 제목, 내용, 작성자, 사진 파일, 작성 일시 지정해서 데이터베이스에 추가하는 함수
                break;
            case 2:
                int doNext = checkBoardDetail(conn);
                //해당 상세 조회 함수로 이동하는 곳. 리턴값이 -1이 아니면 이 다음으로 수정, 삭제 수행함
                if (doNext != -1) {
                    TbManager.setMenuStatus(2);
                    detailNum = doNext;
                }
                break;
            case 3:
                multiDelete(conn);
                //다중 삭제 함수 구현
                break;
            case 4:
                TbManager.setMenuStatus(0);
                System.out.println("메인 메뉴로 이동합니다.");
                break;
            default:
                System.out.println("1~4 의 값을 입력하세요");
        }
        return detailNum;
    }

    public void createContents(Connection conn) {
        TbBoard newTbBoard = new TbBoard();
        TbContent newTbContent = new TbContent();
        Scanner createScanner = new Scanner(System.in);

        System.out.print("식별 id를 입력하세요: ");
        String inputIdFile = createScanner.nextLine();
        newTbBoard.setIdFile(inputIdFile);
        newTbContent.setIdFile(inputIdFile);

        System.out.print("글 제목을 입력하세요: ");
        String inputNmTitle = createScanner.nextLine();
        newTbBoard.setNmTitle(inputNmTitle);

        System.out.print("글 내용을 입력하세요: ");
        String inputNmContent = multiLineStatement(new Scanner(System.in));
        newTbBoard.setNmcontent(inputNmContent);


        System.out.print("저장할 파일이 있습니까? y/n ");
        String isFile = createScanner.nextLine();
        String fullPath = "";
        if (Objects.equals(isFile, "y")) {
            System.out.println("저장할 파일의 경로를 입력하세요");
            fullPath = createScanner.nextLine();
            String[] path = fullPath.split("\\\\");
            StringBuilder stringBuilder = new StringBuilder();
            String fileNameExt = "";
            String fileName = "";
            String ext = "";
            for (int i = 0; i < path.length; i++) {

                if (i == path.length - 1) {

                    fileNameExt = path[i];
                    String[] fneArr = fileNameExt.split("\\.");
                    fileName = fneArr[0];
                    ext = fneArr[1];
                }
            }
//            System.out.println(fileNameExt);
//            System.out.println(fileName);
//            System.out.println(ext);
            newTbContent.setNmOrdFile(fileName);
            newTbContent.setNmFileExt(ext);
        }
        String insertSql = "";
        if (isFile.equals("y")) {
            insertSql = "insert into TB_CONTENT(id_file, nm_org_file, bo_save_file, nm_file_ext, da_save, cn_hit) " +
                    "values(?,?,?,?,SYSDATE,0)";
            try {
                PreparedStatement pstmt = conn.prepareStatement(insertSql);
                pstmt.setString(1, newTbContent.getIdFile());
                pstmt.setString(2, newTbContent.getNmOrdFile());
                try {
                    pstmt.setBlob(3, new FileInputStream(fullPath));
                } catch (SQLException | FileNotFoundException e) {
                    e.printStackTrace();
                }
                pstmt.setString(4, newTbContent.getNmFileExt());

                int rows = pstmt.executeUpdate();
                System.out.println(rows + "행이 TB_CONTENT에 저장");

                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (
                    SQLException e) {
                e.printStackTrace();
            }
        }

        insertSql = "insert into TB_BOARD(nb_board, nm_title, nm_content, nm_writer, da_write, cn_hit, id_file) " +
                "values(SEQ_TB_BOARD.NEXTVAL, ?,?,'나',SYSDATE, 0, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, newTbBoard.getNmTitle());
            pstmt.setString(2, newTbBoard.getNmcontent());
            pstmt.setString(3, newTbBoard.getIdFile());
            int rows = pstmt.executeUpdate();
            System.out.println(rows + "행이 TB_BOARD에 저장");
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        C:\Users\USER\Pictures\Screenshots\스크린샷 2024-03-15 172338.png


    }

    public int checkBoardDetail(Connection conn) {
        System.out.println("------------------------------------------------");
        System.out.print("상세 조회할 게시글의 식별번호를 입력하세요.>");
        int nbNum = -1;
        int checkNum = numInputValid();
        String searchSql = "select nb_board, nm_title, nm_content, cn_hit from TB_BOARD where nb_board=" + checkNum;
        try {
            PreparedStatement pstmt = conn.prepareStatement(searchSql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("-----------------------------");
                System.out.println("번호: " + rs.getInt(1));
                System.out.println("제목: " + rs.getString(2));
                System.out.println("내용: " + rs.getString(3));
                System.out.println("조회수: " + rs.getInt(4));
                nbNum = rs.getInt(1);
                int hitCnt = rs.getInt(4);
                hitCnt += 1;
                String updateSql = new StringBuilder()
                        .append("update tb_board set ")
                        .append("cn_hit=? ")
                        .append("where nb_board=?")
                        .toString();
                pstmt = conn.prepareStatement(updateSql);
                pstmt.setInt(1, hitCnt);
                pstmt.setInt(2, nbNum);

                int rows = pstmt.executeUpdate();
                System.out.println(nbNum + "번 게시물을 조회");
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (nbNum == -1) {
            System.out.println("해당 게시물 없음");
        }
        return nbNum;
    }

    public void multiDelete(Connection conn) {
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


            String searchSql = "select id_file from tb_board where nb_board=" + tryNum;
            try {
                PreparedStatement pst = conn.prepareStatement(searchSql);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    String targetID = rs.getString(1);
                    String deleteContentSql = new StringBuilder()
                            .append("delete from tb_content where id_file=?").toString();
                    PreparedStatement pstDel = conn.prepareStatement(deleteContentSql);
                    pstDel.setString(1, targetID);
                    pstDel.executeUpdate();
                    try {
                        pstDel.close();
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    pst.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }








            String deleteBoardSql = "delete from TB_BOARD where nb_board =" + tryNum;
            try {
                PreparedStatement pstmt = conn.prepareStatement(deleteBoardSql);
                pstmt.executeUpdate();
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(tryNum + "번 게시글이 삭제 되었습니다.");
        }
    }

    public void funcDetailMenu(int menu, TbEx tbManager, boolean keep, Connection conn, int detailNum) {
        switch (menu) {
            case 1:
                editBoard(conn, detailNum);
                break;
            case 2:
                deleteBoard(conn, detailNum);
                tbManager.setMenuStatus(1);
                break;
            case 3:
                tbManager.setMenuStatus(0);
                System.out.println("메인 메뉴로 이동합니다.");
                break;
            default:
                System.out.println("1~3 의 값을 입력하세요");
        }
    }

    public void editBoard(Connection conn, int detailNum) {
        Scanner editScanner = new Scanner(System.in);

        System.out.println(detailNum + "번 게시물을 수정합니다.");
        System.out.print(detailNum + "번 게시글의 새로운 제목을 입력하세요.>");
        String editTitle = editScanner.nextLine();

        System.out.print(detailNum + "번 게시글의 새로운 내용을 입력하세요.>");
        String editContent = editScanner.nextLine();
        String editSql = new StringBuilder()
                .append("update tb_board set ")
                .append("nm_title=?, ")
                .append("nm_content=? ")
                .append("where nb_board=? ")
                .toString();
        try {
            PreparedStatement pstmt = conn.prepareStatement(editSql);
            pstmt.setString(1, editTitle);
            pstmt.setString(2, editContent);
            pstmt.setInt(3, detailNum);
            int rows = pstmt.executeUpdate();
            System.out.println(detailNum + "번 게시물이 수정되었습니다.");
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(detailNum + "번 게시글의 제목과 내용이 수정되었습니다.");
    }

    public void deleteBoard(Connection conn, int detailNum) {
        System.out.println(detailNum + "번 게시물을 삭제합니다.");

        String searchSql = "select id_file from tb_board where nb_board=" + detailNum;
        try {
            PreparedStatement pst = conn.prepareStatement(searchSql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String targetID = rs.getString(1);
                String deleteContentSql = new StringBuilder()
                        .append("delete from tb_content where id_file=?").toString();
                PreparedStatement pstDel = conn.prepareStatement(deleteContentSql);
                pstDel.setString(1, targetID);
                pstDel.executeUpdate();
                try {
                    pstDel.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                pst.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        String deleteBoardSql = "delete from tb_board where nb_board=" + detailNum;
        try {
            PreparedStatement pstmt = conn.prepareStatement(deleteBoardSql);
            pstmt.executeUpdate();

            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(detailNum + "번 게시물을 삭제하였습니다.");
    }

    public static String multiLineStatement(Scanner sc) {
        StringBuilder sb = new StringBuilder(); // 문자열을 누적할 StringBuilder 객체 생성
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if ("END".equals(line)) { // 사용자가 "EOF"를 입력하면 반복을 종료
                break;
            }
            sb.append(line).append("\n"); // 입력받은 줄을 StringBuilder 객체에 추가
        }
        return sb.toString(); // 누적된 문자열 반환
    }
}

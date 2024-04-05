package kr.co.javaex.ex22;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class JDBCBoard {
    public static void main(String[] args) throws ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");

            String dbUrl = "jdbc:oracle:thin:@(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.ap-seoul-1.oraclecloud.com))(connect_data=(service_name=g56e711c2a2b221_dinkdb_medium.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))";

            String dbUser = "DA2401";
            String dbPassword = "Data2401";

            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("연결 성공");


            PreparedStatement pstmt = null;

/*
            Board 테이블 생성
            String createTableSql = "CREATE TABLE BOARD" +
                    "(" +
                    "NB_BOARD NUMBER" +
                    ", NM_TITLE VARCHAR2(100)" +
                    ", NM_CONTENT VARCHAR2(100)" +
                    ", NM_WRITER VARCHAR2(20)" +
                    ", DA_WRITE DATE" +
                    ", NM_FILENAME VARCHAR2(20)" +
                    ", BO_FILEDATA BLOB" +
                    ")";

            pstmt = conn.prepareStatement(createTableSql);
            int createTable = pstmt.executeUpdate();
*/

  /*          //data insert 수행
            String InsertSql = "INSERT INTO BOARD(NB_BOARD, NM_TITLE, NM_CONTENT, NM_WRITER, DA_WRITE, NM_FILENAME, BO_FILEDATA)"
                    + "VALUES(SEQ_BOARD.NEXTVAL, ?, ?, ?, SYSDATE, ?, ?)";
            pstmt = conn.prepareStatement(InsertSql, new String[]{"nb_board"});

            pstmt.setString(1,"게시물 작성 테스트2");
            pstmt.setString(2,"게시물 내용2");
            pstmt.setString(3,"홍길동2");
            pstmt.setString(4,"sample2.png");
            try {
                pstmt.setBlob(5, new FileInputStream("C:/Users/USER/Pictures/Screenshots/스크린샷 2024-03-15 172338.png"));
            }catch (FileNotFoundException e) {
                System.out.println("파일 못찾음");
                e.printStackTrace();
            }

            int rows = pstmt.executeUpdate();
            System.out.println("저장된 행 수: " + rows);


            if (rows !=0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int nbBoard = rs.getInt(1);
                    System.out.println("nb_board(seq_board sequence 값: " + nbBoard);
                }
                rs.close();
            }
*/

/*
            //update 수행
            String updateSql = new StringBuilder()
                    .append("UPDATE board SET ")
                    .append("nm_title=?, ")
                    .append("nm_content=? ")
                    .append("WHERE nb_board=? ")
                    .toString();

            pstmt = conn.prepareStatement(updateSql);
            pstmt.setString(1, "게시물 수정 테스트");
            pstmt.setString(2, "게시물 내용 수정");
            pstmt.setInt(3, 2);

            int rows = pstmt.executeUpdate();
            System.out.println("수정된 행 수 : " + rows);
*/

/*
            //delete 삭제
            String deleteSql = new StringBuilder()
                    .append("DELETe FROM board ")
                    .append("WHERE nb_board=? ")
                    .toString();
            pstmt = conn.prepareStatement(deleteSql);
            pstmt.setInt(1, 3);
            int rows = pstmt.executeUpdate();
            System.out.println("삭제된 행 수: " + rows);
*/


            String selectSql = "SELECT nb_board, nm_title, nm_content, nm_writer "
                    + "FROM board "
                    + "Where nb_board IN(1,2,4,5)";
            pstmt = conn.prepareStatement(selectSql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("-----------------------------");
                System.out.println("번호: " + rs.getInt(1));
                System.out.println("제목: " + rs.getString(2));
                System.out.println("내용: " + rs.getString(3));
                System.out.println("작성자: " + rs.getString(4));
                System.out.println("-----------------------------");
            }


            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}

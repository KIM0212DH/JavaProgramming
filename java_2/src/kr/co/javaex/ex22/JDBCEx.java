package kr.co.javaex.ex22;

import java.sql.*;

public class JDBCEx {
    public static void main(String[] args) throws ClassNotFoundException {

        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");

//            String localDBUrl = "jdbc:oracle:thin:@192.169.119.119/1521/dinkdb";
//            String localDbUser = "scott";
//            String localDbPassword = "tiger";


//            String dbUrl = "jdbc:oracle:thin:@dinkdb_medium?TNS_ADMIN=C://SQLDEV/Wallet_DinkDB.zip";
            String dbUrl = "jdbc:oracle:thin:@(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.ap-seoul-1.oraclecloud.com))(connect_data=(service_name=g56e711c2a2b221_dinkdb_medium.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))";

            String dbUser = "DA2401";
            String dbPassword = "Data2401";

            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("연결 성공");


            PreparedStatement pstmt = null;

//            USER 테이블 생성
//            String createTableSql = "CREATE TABLE USERS\n" +
//                    "(\n" +
//                    "  ID_USER VARCHAR2(20)\n" +
//                    ", NM_USER VARCHAR2(20)\n" +
//                    ", ID_PASWD VARCHAR2(20)\n" +
//                    ", NB_AGE INT\n" +
//                    ", ID_EMAIL VARCHAR2(20)\n" +
//                    ")";
//            pstmt = conn.prepareStatement(createTableSql);
//            int createTable = pstmt.executeUpdate();


            // 테스트 레코드 추가
//            String insertValueSql = "INSERT INTO USERS(ID_USER, NM_USER,
//            ID_PASWD, NB_AGE,ID_EMAIL)" + "VALUES(?,?,?,?,?)";
//            pstmt = conn.prepareStatement(insertValueSql);
//            pstmt.setString(1, "test1");
//            pstmt.setString(2, "홍길동");
//            pstmt.setString(3, "test1");
//            pstmt.setInt(4, 25);
//            pstmt.setString(5, "test1@test.com");
//
//            int rows = pstmt.executeUpdate();
//            System.out.println("저장된 행 수: " + rows);

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

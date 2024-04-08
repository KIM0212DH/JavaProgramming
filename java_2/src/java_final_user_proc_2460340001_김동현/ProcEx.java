package java_final_user_proc_2460340001_김동현;

import java_final_user_proc_2460340001_김동현.ecommerce.TbOrder;
import java_final_user_proc_2460340001_김동현.product.TbProduct;
import java_final_user_proc_2460340001_김동현.user.TbUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProcEx {
    public static void main(String[] args) {
        ProcEx eComManager = new ProcEx();
        Connection connection = makeConnect();

        TbUser tbUser = new TbUser();
        int keep = 1;
        while (keep > 0) {

            if (eComManager.getMenuStatus() == 0) {
                int menu = tbUser.showLoginMenu();
                keep = tbUser.funcLoginMenu(menu, keep, connection, tbUser);
                if (keep == 2) {
                    eComManager.setMenuStatus(1);   // 로그인 성공 시
                }
            } else if (eComManager.getMenuStatus() == 1) {
                TbOrder tbOrder = new TbOrder();
                if (tbUser.getCdUserType().equals("10")) {
                    // 일반 사용자

                } else if (tbUser.getCdUserType().equals("20")) {
                    // 관리자 전용
                    TbProduct tbProduct = new TbProduct();
                    System.out.println("관리자 페이지로 입장합니다.");
                    int menu = tbUser.showAdminMenu();
                    keep = tbProduct.funcAdminMenu(menu, keep, connection, tbUser);
                    if (keep == 0) {
                        eComManager.setMenuStatus(0);
                        keep = 1;
                    }
                }
            }
        }

    }

    int menuStatus = 0;

    public int getMenuStatus() {
        return menuStatus;
        // 0은 로그인/회원가입 메뉴
        // 11은 관리자 로그인 후 화면 (카테고리 조회1 (카테고리 조회2), 상품 추가, 상품 수정, 상품 삭제, 주문, 카테고리 삭제, 로그아웃)
        // 12는 카테고리 조회2
        // 21는 일반 로그인 후 화면
        // 31는 주문 화면 ()
    }

    public void setMenuStatus(int menuStatus) {
        this.menuStatus = menuStatus;
    }

    public static Connection makeConnect() {
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

}
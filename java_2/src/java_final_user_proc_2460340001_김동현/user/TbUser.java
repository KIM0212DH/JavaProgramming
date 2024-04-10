package java_final_user_proc_2460340001_김동현.user;

import java_final_user_proc_2460340001_김동현.ProcEx;
import java_final_user_proc_2460340001_김동현.category.TbCategory;
import java_final_user_proc_2460340001_김동현.ecommerce.TbBasket;
import java_final_user_proc_2460340001_김동현.ecommerce.TbBasketItem;
import java_final_user_proc_2460340001_김동현.ecommerce.TbOrder;
import java_final_user_proc_2460340001_김동현.ecommerce.TbOrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java_final_user_proc_2460340001_김동현.ProcEx.numInputValid;

public class TbUser {
    String noUser;
    String idUser;
    String nmUser;
    String nmPaswd;
    String nmEmail;
    String stStatus;
    String cdUserType;

    public String getNoUser() {
        return noUser;
    }

    public void setNoUser(String noUser) {
        this.noUser = noUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNmUser() {
        return nmUser;
    }

    public void setNmUser(String nmUser) {
        this.nmUser = nmUser;
    }

    public String getNmPaswd() {
        return nmPaswd;
    }

    public void setNmPaswd(String nmPaswd) {
        this.nmPaswd = nmPaswd;
    }

    public String getNmEmail() {
        return nmEmail;
    }

    public void setNmEmail(String nmEmail) {
        this.nmEmail = nmEmail;
    }

    public String getStStatus() {
        return stStatus;
    }

    public void setStStatus(String stStatus) {
        this.stStatus = stStatus;
    }

    public String getCdUserType() {
        return cdUserType;
    }

    public void setCdUserType(String cdUserType) {
        this.cdUserType = cdUserType;
    }

    public int showLoginMenu() {
        System.out.println("------------------------------------------------");
        System.out.println("관리자 아이디/비밀번호 Admin1/Admin1, 일반 사용자 User1/User1");
        System.out.println("1.로그인, 2.회원가입, 3.프로그램 종료");
        System.out.print("선택>");
        return numInputValid();
    }

    public int funcLoginMenu(int menu, int keep, Connection conn, TbUser tbUser) {
        switch (menu) {
            case 1:
                //로그인하기 로그인 성공하면 2 반환, 실패하면 1반환
                keep = loginUser(conn, keep, tbUser);
                break;
            case 2:
                keep = createUser(conn, keep);
                //회원 가입하기
                break;
            case 3:
                System.out.println("프로그램 종료");
                keep = -1;
                break;
            default:
                System.out.println("1~3 의 값을 입력하세요");
        }
        return keep;
    }

    public int loginUser(Connection connection, int keep, TbUser tbUser) {
        Scanner loginScanner = new Scanner(System.in);
        System.out.println("------------------------------------------------");
        System.out.println("로그인을 시도합니다.");
        System.out.print("로그인할 ID를 입력하세요.>");
        String tryId = loginScanner.nextLine();
        System.out.print("비밀번호를 입력하세요.>");
        String tryPW = loginScanner.nextLine();
        String selectUser = "select id_user, nm_passwd, CD_USER_TYPE, no_user, nm_user from tb_user " +
                "where id_user = ? and nm_passwd = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectUser);
            pstmt.setString(1, tryId);
            pstmt.setString(2, tryPW);

            ResultSet rs = pstmt.executeQuery();
            int rowcount = 0;
            String targetId = "";
            String targetPW = "";
            String targetType = "";
            String targetNo = "";
            String targetNM = "";
            while (rs.next()) {
                rowcount++;
                targetId = rs.getString(1);
                targetPW = rs.getString(2);
                targetType = rs.getString(3);
                targetNo = rs.getString(4);
                targetNM = rs.getString(5);
            }
            if (rowcount == 1) {
                tbUser.setIdUser(targetId);
                tbUser.setNmPaswd(targetPW);
                tbUser.setCdUserType(targetType);
                tbUser.setNoUser(targetNo);
                tbUser.setNmUser(targetNM);
                System.out.println(tbUser.getIdUser() + " 계정에 로그인 하였습니다.");
                keep = 2;
            } else {
                System.out.println("잘못된 로그인 정보 입니다.");
            }


            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return keep;
        }


    }


    public int createUser(Connection connection, int keep) {
        Scanner createUserScanner = new Scanner(System.in);
        System.out.println("------------------------------------------------");
        System.out.println("회원가입을 시도합니다.");
        System.out.print("회원 가입할 ID를 입력하세요.>");
        String newId = createUserScanner.nextLine();
        System.out.print("회원 가입할 비밀번호를 입력하세요.>");
        String newPW = createUserScanner.nextLine();
        if (!checkValidId(newId) || !checkValidPassword(newPW)) {
            System.out.println("사용 가능한 ID 혹은 비밀번호가 아닙니다.\n 회원 가입 실패");
            return 1;
        }
        System.out.print("회원 가입할 사용자 이름을 입력하세요.>");
        String newName = createUserScanner.nextLine();
        System.out.print("회원 가입할 사용자 이메일을 입력하세요.>");
        String newEmail = createUserScanner.nextLine();

        String insertUser = "insert into TB_USER(no_user, id_user, nm_user, nm_passwd, nm_email, st_status, cd_user_type)" +
                "values('PD'||LPAD(seq_tb_user.nextval, 5, '0'), ?, ?, ?, ?, 'ST01', '10')";

        try {
            PreparedStatement pstmt = connection.prepareStatement(insertUser);
            pstmt.setString(1, newId);
            pstmt.setString(2, newName);
            pstmt.setString(3, newPW);
            pstmt.setString(4, newEmail);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + "개 user가 추가되었습니다.");
            keep = 1;
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("user 추가에 실패하였습니다.");
            keep = 1;
            e.printStackTrace();
        } finally {
            return keep;
        }
    }


    public boolean checkValidId(String id) {
        if (id.length() >= 5 && id.length() <= 15) {
            for (int i = 0; i < id.length(); ++i) {
                char c = id.charAt(i);
                if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9')) {
                    System.out.println("허용된 숫자나 영어가 아닙니다.");
                    return false;
                }
            }

            return true;
        } else {
            System.out.println("허용된 길이의 id가 아닙니다.");
            return false;
        }
    }

    public boolean checkValidPassword(String password) {
        if (password.length() >= 5 && password.length() <= 15) {
            int upperCnt = 0;
            int lowerCnt = 0;
            int numCnt = 0;

            for (int i = 0; i < password.length(); ++i) {
                char c = password.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    ++lowerCnt;
                }

                if (c >= 'A' && c <= 'Z') {
                    ++upperCnt;
                }

                if (c >= '0' && c <= '9') {
                    ++numCnt;
                }
            }

            if (upperCnt != 0 && lowerCnt != 0 && numCnt != 0) {
                return true;
            } else {
                System.out.println("영어 대소문자, 숫자 입력이 필요합니다.");
                return false;
            }
        } else {
            System.out.println("허용된 길이의 password가 아닙니다.");
            return false;
        }
    }

    public int showAdminMenu() {
        System.out.println("------------------------------------------------");
        System.out.println("1.카테고리 조회, 2.로그아웃");
        System.out.print("선택>");
        return numInputValid();
    }

    public int showGeneralMenu() {
        System.out.println("------------------------------------------------");
        System.out.println("1.카테고리 조회, 2.로그아웃");
        System.out.print("선택>");
        return numInputValid();
    }

    public int funcGeneralMenu(int menu, int keep, Connection connection, TbBasket tbBasket, TbBasketItem tbBasketItem, TbOrder tbOrder, TbUser tbUser) {
        switch (menu) {
            case 1:
                // 카테고리 조회
                // 카테고리 조회하고 조회한 카테고리 안에서 카트에 추가, 카트에 삭제, 카트 주문, 개별 상품 주문 수행
                TbCategory targetCategory = new TbCategory();
                targetCategory.oneDepthlistUpCategory(connection);
                System.out.print("조회하고 싶은 하위 카테고리를 선택하세요.>");
                int detailCategory = new Scanner(System.in).nextInt();
                targetCategory.twoDepthListUpCategory(connection, detailCategory);

                System.out.print("상세 조회하고 싶은 카테고리를 선택하세요.>");
                detailCategory = new Scanner(System.in).nextInt();
                TbCategory detailTargetCategory = new TbCategory();
                detailTargetCategory = targetCategory.selectCategory(connection, detailCategory);
                System.out.println("------------------------------------------------");
                System.out.print("1.장바구니 조회, 2.장바구니에 상품 추가, 3.장바구니에서 상품 삭제, 4.장바구니 주문, 5.개별 상품 주문, 6.상위 메뉴.>");
                int doAction = ProcEx.numInputValid();
                String nowUserNo = tbUser.getNoUser();
                int nowBasketNo = tbBasketItem.getNbBasketItem();
                if (doAction == 1) {
                    // 장바구니 내부 조회
                    System.out.println("------------------------------------------------");
                    System.out.println(tbUser.getNmUser() + "의 장바구니 품목을 확인합니다.");
                    tbBasketItem.checkBasketItem(connection, nowUserNo, nowBasketNo);
                } else if (doAction == 2) {
                    // 카트에 상품 추가
                    System.out.print("장바구니에 추가하고 싶은 상품 번호를 입력하세요. 예)PT0000011 >");
                    String nowProduct = new Scanner(System.in).nextLine();
                    System.out.print("장바구니에 추가하고 싶은 개수를 입력하세요.>");
                    int nowAmount = ProcEx.numInputValid();
                    tbBasketItem.insertIntoBasketItem(connection, nowUserNo, nowBasketNo, nowProduct, nowAmount);
                    tbBasket.calculateBasket(connection, nowUserNo, nowBasketNo);
                } else if (doAction == 3) {
                    // 카트에서 상품 삭제
                    System.out.println();
                    System.out.println("------------------------------------------------");
                    System.out.println(tbUser.getNmUser() + "의 장바구니 품목을 확인합니다.");
                    tbBasketItem.checkBasketItem(connection, nowUserNo, nowBasketNo);
                    System.out.print("삭제할 장바구니 품목 식별번호를 입력하세요.>");
                    String deleteTargetItem = new Scanner(System.in).nextLine();
                    tbBasketItem.deleteFromBasketItem(connection, nowBasketNo, deleteTargetItem);
                } else if (doAction == 4) {
                    // 카트 주문
                    System.out.println("------------------------------------------------");
                    System.out.println("장바구니에 담긴 모든 물건을 구입합니다.");
                    TbOrder newOrder = new TbOrder();
                    TbOrderItem newOrderItem = new TbOrderItem();
                    ArrayList<String> waitingOrder = tbOrder.getOrderProductList(connection, nowUserNo);
                    int totalDeliveryFee = 0;
                    int totalPrice = 0;
                    for (String wo : waitingOrder) {
                        System.out.println("장바구니에 담긴 " + wo + "를 구매합니다.");
                        totalDeliveryFee += newOrder.getDeliFee(connection, wo);
                        totalPrice += newOrder.getOrderAmount(connection, wo, nowBasketNo);
                    }
                    String nowSeq = newOrder.orderingBasket(connection, tbUser, totalDeliveryFee, totalPrice);
                    for (String wo: waitingOrder) {
                        newOrderItem.orderingBasketItem(connection, nowSeq, wo, tbUser, nowBasketNo);
                    }
//                    System.out.println(deliverPeriod);
                } else if (doAction == 5) {
                    // 단일 상품 주문
                    TbOrder newOrder = new TbOrder();
                    TbOrderItem newOrderItem = new TbOrderItem();
                    System.out.println("------------------------------------------------");
                    System.out.print("주문 하고자하는 상품의 상품번호를 입력하세요.>");
                    String orderProduct = new Scanner(System.in).nextLine();
                    System.out.print("주문 하고자하는 개수를 입력하세요.>");
                    int orderAmount = ProcEx.numInputValid();
                    int deliveryFee = tbOrder.getDeliFee(connection, orderProduct);
                    String orderPerson = tbUser.getNmUser();
                    String nowSeq = newOrder.ordering(connection, tbUser, orderProduct, orderAmount, deliveryFee, orderPerson);
                    newOrderItem.orderingItem(connection, nowSeq, orderProduct, tbUser, orderAmount, deliveryFee);


                } else {
                    break;
                }

                break;
            case 2:
                // 로그아웃
                keep = 0;
                break;
            default:

        }
        return keep;
    }

    public static int getMax(int num1, int num2) {
        return num1 > num2 ? num1 : num2;
    }
}

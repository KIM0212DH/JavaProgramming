package java_final_user_proc_2460340001_김동현.product;

import java_final_user_proc_2460340001_김동현.category.TbCategory;
import java_final_user_proc_2460340001_김동현.user.TbUser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TbProduct {
    String noProduct;
    String nmProduct;
    String nmDetailExplain;
    String dtStartDate;
    String idFile;
    String dtEndDate;
    int qtCustomer;
    int qtSalePrice;
    int qtStock;
    int qtDeliveryFee;

    public String getNoProduct() {
        return noProduct;
    }

    public void setNoProduct(String noProduct) {
        this.noProduct = noProduct;
    }

    public String getNmProduct() {
        return nmProduct;
    }

    public void setNmProduct(String nmProduct) {
        this.nmProduct = nmProduct;
    }

    public String getNmDetailExplain() {
        return nmDetailExplain;
    }

    public void setNmDetailExplain(String nmDetailExplain) {
        this.nmDetailExplain = nmDetailExplain;
    }

    public String getDtStartDate() {
        return dtStartDate;
    }

    public void setDtStartDate(String dtStartDate) {
        this.dtStartDate = dtStartDate;
    }

    public String getIdFile() {
        return idFile;
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }

    public String getDtEndDate() {
        return dtEndDate;
    }

    public void setDtEndDate(String dtEndDate) {
        this.dtEndDate = dtEndDate;
    }

    public int getQtCustomer() {
        return qtCustomer;
    }

    public void setQtCustomer(int qtCustomer) {
        this.qtCustomer = qtCustomer;
    }

    public int getQtSalePrice() {
        return qtSalePrice;
    }

    public void setQtSalePrice(int qtSalePrice) {
        this.qtSalePrice = qtSalePrice;
    }

    public int getQtStock() {
        return qtStock;
    }

    public void setQtStock(int qtStock) {
        this.qtStock = qtStock;
    }

    public int getQtDeliveryFee() {
        return qtDeliveryFee;
    }

    public void setQtDeliveryFee(int qtDeliveryFee) {
        this.qtDeliveryFee = qtDeliveryFee;
    }
//1.카테고리 조회, 2.상품 추가, 3.상품 수정, 4.상품 삭제, 5.카테고리 삭제, 6.주문, 7.로그아웃


    public int funcAdminMenu(int menu, int keep, Connection connection, TbUser tbUser) {
        switch (menu) {
            case 1:
                // 카테고리 조회
                // 카테고리 조회하고 조회한 카테고리 안에서 상품 추가, 수정, 삭제, 카테고리 삭제 수행
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
                System.out.print("1.상품 추가, 2.상품 수정, 3.상품 삭제, 4.카테고리 삭제, 5.상위 메뉴.>");
                int doAction = new Scanner(System.in).nextInt();
                if (doAction == 1) {    // 상품 추가
                    TbProduct newProduct = new TbProduct();
                    System.out.println("무결성 확인 :" + detailCategory);
                    newProduct.addProduct(connection, detailCategory);
                } else if (doAction == 2) {
                    // 상품 수정
                    TbProduct oldProduct = new TbProduct();
                    oldProduct.editProduct(connection, targetCategory.getNbCategory());
                } else if (doAction == 3) {
                    // 상품 삭제
                    TbProduct product = new TbProduct();
                    product.deleteProduct(connection);
                } else if (doAction == 4) {
                    // 카테고리 삭제
                    targetCategory.deleteCategory(connection);
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


    public void addProduct(Connection connection, int categoryNum) {
        TbProduct newProduct = new TbProduct();
        Scanner addProductScanner = new Scanner(System.in);
        System.out.println("------------------------------------------------");
        System.out.println("상품 추가를 시도합니다.");
        System.out.print("추가할 상품명을 입력하세요.>");
        newProduct.setNmProduct(addProductScanner.nextLine());
        System.out.print("상품의 상세 설명을 입력하세요.>");
        newProduct.setNmDetailExplain(addProductScanner.nextLine());
        System.out.print("상품의 판매 시작 일자를 입력하세요.>");
        newProduct.setDtStartDate(addProductScanner.nextLine());
        System.out.print("컨텐츠 식별 ID를 입력하세요.>");
        newProduct.setIdFile(addProductScanner.nextLine());
        System.out.print("상품의 판매 종료 일자를 입력하세요.>");
        newProduct.setDtEndDate(addProductScanner.nextLine());

        Scanner addProductScanner2 = new Scanner(System.in);
        System.out.print("상품의 소비자 가격을 입력하세요.>");
        newProduct.setQtCustomer(addProductScanner2.nextInt());
        System.out.print("상품의 판매 가격을 입력하세요.>");
        newProduct.setQtSalePrice(addProductScanner2.nextInt());
        System.out.print("상품의 재고 수량을 입력하세요.>");
        newProduct.setQtStock(addProductScanner2.nextInt());
        System.out.print("상품의 배송비 금액을 입력하세요.>");
        newProduct.setQtDeliveryFee(addProductScanner2.nextInt());

        String insertProduct = "insert into TB_PRODUCT(no_product, nm_product, nm_detail_explain, dt_start_date, id_file, dt_end_date, qt_customer, qt_sale_price, qt_stock, qt_delivery_fee) " +
                "values('PT'||LPAD(seq_tb_product.nextval, 7, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String getSeq = "select no_product from tb_product where nm_product = ?";
        String nowSeq = "";


        String insertMappingProduct = "insert into tb_category_product_mapping(nb_category, no_product) " +
                "values(?, 'PT'||LPAD(?, 7, '0'))";
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        try {
            pstmt = connection.prepareStatement(insertProduct);
            pstmt.setString(1, newProduct.getNmProduct());
            pstmt.setString(2, newProduct.getNmDetailExplain());
            pstmt.setString(3, newProduct.getDtStartDate());
            pstmt.setString(4, newProduct.getIdFile());

//            try {
//                pstmt.setBlob(4, new FileInputStream(newProduct.getIdFile()));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }

            pstmt.setString(5, newProduct.getDtEndDate());
            pstmt.setInt(6, newProduct.getQtCustomer());
            pstmt.setInt(7, newProduct.getQtSalePrice());
            pstmt.setInt(8, newProduct.getQtStock());
            pstmt.setInt(9, newProduct.getQtDeliveryFee());
            int rows = pstmt.executeUpdate();
            System.out.println(rows + "개 product가 추가되었습니다.");


            try {

                PreparedStatement getSeqPstmt = connection.prepareStatement(getSeq);
                getSeqPstmt.setString(1, newProduct.getNmProduct());
                ResultSet rs = getSeqPstmt.executeQuery();
                while (rs.next()) {
                    nowSeq = rs.getString(1);
                    System.out.println("ns: " + nowSeq);
                }
                try {
                    getSeqPstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("ddd");
                e.printStackTrace();
            }

            pstmt.close();



            pstmt2 = connection.prepareStatement(insertMappingProduct);
            System.out.println(newProduct.getNmProduct());

            pstmt2.setInt(1, categoryNum);
            pstmt2.setString(2, nowSeq);
            System.out.println("nowSeq: " + nowSeq);
            System.out.println("categoryNum: " + categoryNum);


            rows = pstmt2.executeUpdate();
            System.out.println(rows + "개 product가 category_product_mapping에 추가되었습니다.");
            try {
                pstmt2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("상품 추가에 실패하였습니다.");
        }


    }

    public void editProduct(Connection connection, int categoryNum) {
        String editing = "";
        TbProduct oldProduct = new TbProduct();
        String findSql = "select no_product from tb_product where no_product =?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(findSql);
            System.out.print("수정하고 싶은 상품의 상품 번호를 입력하세요.>");
            editing = new Scanner(System.in).nextLine();
            pstmt.setString(1, editing);
            ResultSet rs = pstmt.executeQuery();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
                System.out.println("변경할 상품은 " + rs.getString(1) + " 입니다.");
            }
            if (rowCount != 1) {
                return;
            }
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Scanner editProductScanner = new Scanner(System.in);
        System.out.println("------------------------------------------------");
        System.out.println("상품 변경을 시도합니다.");
        System.out.print("변경할 상품명을 입력하세요.>");
        oldProduct.setNmProduct(editProductScanner.nextLine());
        System.out.print("변경할 상품의 상세 설명을 입력하세요.>");
        oldProduct.setNmDetailExplain(editProductScanner.nextLine());
        System.out.print("변경할 상품의 판매 시작 일자를 입력하세요.>");
        oldProduct.setDtStartDate(editProductScanner.nextLine());
        System.out.print("변경할 컨텐츠 식별 ID를 입력하세요.>");
        oldProduct.setIdFile(editProductScanner.nextLine());
        System.out.print("변경할 상품의 판매 종료 일자를 입력하세요.>");
        oldProduct.setDtEndDate(editProductScanner.nextLine());

        Scanner editProductScanner2 = new Scanner(System.in);
        System.out.print("변경할 상품의 소비자 가격을 입력하세요.>");
        oldProduct.setQtCustomer(editProductScanner2.nextInt());
        System.out.print("변경할 상품의 판매 가격을 입력하세요.>");
        oldProduct.setQtSalePrice(editProductScanner2.nextInt());
        System.out.print("변경할 상품의 재고 수량을 입력하세요.>");
        oldProduct.setQtStock(editProductScanner2.nextInt());
        System.out.print("변경할 상품의 배송비 금액을 입력하세요.>");
        oldProduct.setQtDeliveryFee(editProductScanner2.nextInt());

        String updateSql = new StringBuilder()
                .append("update tb_product set ")
                .append("nm_product=?, ")
                .append("nm_detail_explain=?, ")
                .append("dt_start_date=?, ")
                .append("id_file=?, ")
                .append("dt_end_date=?, ")
                .append("qt_customer=?, ")
                .append("qt_sale_price=?, ")
                .append("qt_stock=?, ")
                .append("qt_delivery_fee=? ")
                .append("where no_product=? ")
                .toString();
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateSql);
            pstmt.setString(1, oldProduct.getNmProduct());
            pstmt.setString(2, oldProduct.getNmDetailExplain());
            pstmt.setString(3, oldProduct.getDtStartDate());
            pstmt.setString(4, oldProduct.getIdFile());
//                pstmt.setBlob(4, new FileInputStream("C:/Users/USER/Pictures/Screenshots/4.png"));

            pstmt.setString(5, oldProduct.getDtEndDate());
            pstmt.setInt(6, oldProduct.getQtCustomer());
            pstmt.setInt(7, oldProduct.getQtSalePrice());
            pstmt.setInt(8, oldProduct.getQtStock());
            pstmt.setInt(9, oldProduct.getQtDeliveryFee());
            pstmt.setString(10, editing);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + "개 게시물을 수정하였습니다.");
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(Connection connection) {
        String deleting = "";
        String findSql = "delete from tb_product where no_product =?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(findSql);
            System.out.print("삭제하고 싶은 상품의 상품 번호를 입력하세요.>");
            deleting = new Scanner(System.in).nextLine();
            pstmt.setString(1, deleting);
            pstmt.executeQuery();
            System.out.println(deleting + "번 상품을 삭제합니다.");

            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

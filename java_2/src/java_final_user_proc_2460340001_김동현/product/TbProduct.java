package java_final_user_proc_2460340001_김동현.product;

import java_final_user_proc_2460340001_김동현.ProcEx;
import java_final_user_proc_2460340001_김동현.category.TbCategory;
import java_final_user_proc_2460340001_김동현.user.TbUser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class TbProduct {
    String noProduct;
    String nmProduct;
    Clob nmDetailExplain;
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

    public Clob getNmDetailExplain() {
        return nmDetailExplain;
    }

    public void setNmDetailExplain(Clob nmDetailExplain) {
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
                int doAction = ProcEx.numInputValid();
                if (doAction == 1) {    // 상품 추가
                    TbProduct newProduct = new TbProduct();
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
        TbContent newTbContent = new TbContent();
        Scanner addProductScanner = new Scanner(System.in);
        System.out.println("------------------------------------------------");
        System.out.println("상품 추가를 시도합니다.");
        System.out.print("추가할 상품명을 입력하세요.>");
        newProduct.setNmProduct(addProductScanner.nextLine());
        System.out.print("상품의 상세 설명을 입력하세요.입력 종료는 END 입력.>");
        String detailExplain = TbProduct.multiLineStatement(new Scanner(System.in));
        try {
            Clob newClob = TbProduct.convertStringToClob(detailExplain, connection);
            newProduct.setNmDetailExplain(newClob);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("저장할 사진의 경로를 입력하세요.>");
        String fullPath = new Scanner(System.in).nextLine();
        String insertContent = "insert into tb_content(id_file, bo_save_file, da_save, cn_hit, nm_org_file) " +
                "values('IF'||LPAD(SEQ_TB_CONTENT.nextval,5,'0'), ?, SYSDATE, 0, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(insertContent);
            try {
                pstmt.setBlob(1, new FileInputStream(fullPath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            pstmt.setString(2, fullPath);
            int rows = pstmt.executeUpdate();
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String nowIdFile = "";
        String getIdFile = "select id_file from tb_content where nm_org_file = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(getIdFile);
            pstmt.setString(1, fullPath);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                nowIdFile = rs.getString(1);
                break;
            }
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            newProduct.setIdFile(nowIdFile);
        }


        System.out.print("상품의 판매 시작 일자를 입력하세요.>");
        newProduct.setDtStartDate(addProductScanner.nextLine());

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
                "values(?, ?)";
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        try {
            pstmt = connection.prepareStatement(insertProduct);
            pstmt.setString(1, newProduct.getNmProduct());
            pstmt.setClob(2, newProduct.getNmDetailExplain());
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
                }
                try {
                    getSeqPstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            pstmt.close();


            pstmt2 = connection.prepareStatement(insertMappingProduct);

            pstmt2.setInt(1, categoryNum);
            pstmt2.setString(2, nowSeq);



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
        System.out.print("변경할 상품의 상세 설명을 입력하세요. 입력 종료는 END 입력.>");
        String editDetailExplain = TbProduct.multiLineStatement(new Scanner(System.in));
        try {
            Clob newClob = TbProduct.convertStringToClob(editDetailExplain, connection);
            oldProduct.setNmDetailExplain(newClob);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String nowIdFile = "";
        String getIdFileSql = "select id_file from tb_product where no_product = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(getIdFileSql);
            pstmt.setString(1, editing);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                nowIdFile = rs.getString(1);
                break;
            }
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.print("변경할 상품의 새로운 사진 경로를 입력하세요.>");
        String fullPath = new Scanner(System.in).nextLine();
        String changeBlobSql = "update tb_content set bo_save_file = ?, nm_org_file = ? where id_file = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(changeBlobSql);
            try {
                pstmt.setBlob(1, new FileInputStream(fullPath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            pstmt.setString(2, fullPath);
            pstmt.setString(3, nowIdFile);
            int rows = pstmt.executeUpdate();
            try {
                pstmt.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.print("변경할 상품의 판매 시작 일자를 입력하세요.>");
        oldProduct.setDtStartDate(editProductScanner.nextLine());

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
            pstmt.setClob(2, oldProduct.getNmDetailExplain());
            pstmt.setString(3, oldProduct.getDtStartDate());
            pstmt.setString(4, oldProduct.getDtEndDate());
            pstmt.setInt(5, oldProduct.getQtCustomer());
            pstmt.setInt(6, oldProduct.getQtSalePrice());
            pstmt.setInt(7, oldProduct.getQtStock());
            pstmt.setInt(8, oldProduct.getQtDeliveryFee());
            pstmt.setString(9, editing);
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

    static Clob convertStringToClob(String str, Connection connection) throws SQLException {
        Clob myClob = connection.createClob();
        myClob.setString(1, str);
        return myClob;
    }

}

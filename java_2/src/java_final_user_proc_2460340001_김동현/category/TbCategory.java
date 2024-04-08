package java_final_user_proc_2460340001_김동현.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TbCategory {
    int nbCategory;
    int nbParentCategory;
    String nmCategory;
    int cnLevel;
    int cnOrder;

    public int getNbCategory() {
        return nbCategory;
    }

    public void setNbCategory(int nbCategory) {
        this.nbCategory = nbCategory;
    }

    public int getNbParentCategory() {
        return nbParentCategory;
    }

    public void setNbParentCategory(int nbParentCategory) {
        this.nbParentCategory = nbParentCategory;
    }

    public String getNmCategory() {
        return nmCategory;
    }

    public void setNmCategory(String nmCategory) {
        this.nmCategory = nmCategory;
    }

    public int getCnLevel() {
        return cnLevel;
    }

    public void setCnLevel(int cnLevel) {
        this.cnLevel = cnLevel;
    }

    public int getCnOrder() {
        return cnOrder;
    }

    public void setCnOrder(int cnOrder) {
        this.cnOrder = cnOrder;
    }

    public void oneDepthlistUpCategory(Connection connection) {
        String listUpStr = "select nb_category, nb_parent_category, nm_category, cn_level, cn_order from TB_CATEGORY ";
        TbCategory tbCategory = new TbCategory();
        try {
            PreparedStatement pstmt = connection.prepareStatement(listUpStr);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("------------------------------------------------");
            System.out.println("카테고리들을 출력합니다.");
            while (rs.next()) {
                tbCategory.setNbCategory(rs.getInt(1));
                tbCategory.setNbParentCategory(rs.getInt(2));
                tbCategory.setNmCategory(rs.getString(3));
                tbCategory.setCnLevel(rs.getInt(4));
                tbCategory.setCnOrder(rs.getInt(5));
                if (tbCategory.getNbParentCategory() == 0) {
                    System.out.print("카테고리 번호: " + rs.getInt(1));
                    System.out.print("\t상위 카테고리: " + rs.getInt(2));
                    System.out.print("\t카테고리 이름: " + rs.getString(3));
                    System.out.print("\t카테고리 레벨: " + rs.getInt(4));
                    System.out.println("\t카테고리 순서: " + rs.getInt(5));
                }
            }
        } catch (SQLException e) {
            System.out.println("카테고리 리스트업 오류");
        }
    }

    public void twoDepthListUpCategory(Connection connection, int targetNum) {
        String listUpStr = "select nb_category, nb_parent_category, nm_category, cn_level, cn_order from TB_CATEGORY where nb_parent_category= ?";
        TbCategory tbCategory = new TbCategory();
        try {
            PreparedStatement pstmt = connection.prepareStatement(listUpStr);
            pstmt.setInt(1, targetNum);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("------------------------------------------------");
            System.out.println("하위 카테고리들을 출력합니다.");
            while (rs.next()) {
                tbCategory.setNbCategory(rs.getInt(1));
                tbCategory.setNbParentCategory(rs.getInt(2));
                tbCategory.setNmCategory(rs.getString(3));
                tbCategory.setCnLevel(rs.getInt(4));
                tbCategory.setCnOrder(rs.getInt(5));
                if (tbCategory.getNbParentCategory() == targetNum) {
                    System.out.print("카테고리 번호: " + rs.getInt(1));
                    System.out.print("\t상위 카테고리: " + rs.getInt(2));
                    System.out.print("\t카테고리 이름: " + rs.getString(3));
                    System.out.print("\t카테고리 레벨: " + rs.getInt(4));
                    System.out.println("\t카테고리 순서: " + rs.getInt(5));
                }
            }
        } catch (SQLException e) {
            System.out.println("카테고리 리스트업 오류");
        }

    }

    public TbCategory selectCategory(Connection connection, int detailCategory) {
        TbCategory extractCategory = new TbCategory();
        String listUpStr = "select c.nb_category, c.nb_parent_category, c.nm_category, p.nm_product, p.nm_detail_explain, p.no_product " +
                "from tb_category c " +
                "inner join tb_category_product_mapping m " +
                "on c.nb_category = m.nb_category " +
                "inner join tb_product p " +
                "on p.no_product = m.no_product " +
                "where c.nb_category = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(listUpStr);
            pstmt.setInt(1, detailCategory);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("------------------------------------------------");
            System.out.println("하위 카테고리 내부의 상품을 표시합니다.");
            while (rs.next()) {
                System.out.print("카테고리 번호: " + rs.getInt(1));
                System.out.print("\t부모 카테고리 번호: " + rs.getInt(2));
                System.out.print("\t카테고리 이름: " + rs.getString(3));
                System.out.print("\t상품 번호: " + rs.getString(6));
                System.out.print("\t상품 이름: " + rs.getString(4));
                System.out.print("\t상품 설명: " + rs.getString(5));
                System.out.println();
                extractCategory.setNbCategory(rs.getInt(1));
                extractCategory.setNbParentCategory(rs.getInt(2));
                extractCategory.setNmCategory(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return extractCategory;
    }

    public void deleteCategory(Connection connection){
        System.out.print("삭제하고 싶은 카테고리의 번호를 입력하세요.>");
        int deleting = new Scanner(System.in).nextInt();
        String checkInside = "select count(*) from tb_category_product_mapping where nb_category = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(checkInside);
            pstmt.setInt(1, deleting);

            ResultSet rs = pstmt.executeQuery();
            int rowCount = 0;
            rs.next();
            if (rs.getInt(1) > 0) {
                System.out.println("카테고리 내부의 상품 정보가 있어서 카테고리 삭제가 불가능합니다.");
                return;
            }

            String deleteCategorySql = "delete from tb_category where nb_category=?";
            pstmt = connection.prepareStatement(deleteCategorySql);
            pstmt.setInt(1, deleting);
            pstmt.executeUpdate();
            System.out.println("카테고리 " + deleting+"을 삭제하였습니다.");
            try {
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

}

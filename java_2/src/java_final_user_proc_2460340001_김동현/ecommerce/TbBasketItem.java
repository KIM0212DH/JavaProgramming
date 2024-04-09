package java_final_user_proc_2460340001_김동현.ecommerce;

import java_final_user_proc_2460340001_김동현.product.TbProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TbBasketItem {
    int nbBasketItem;
    int nbBasket;
    int cnBasketItemOrder;
    String noProduct;
    String noUser;
    int qtBasketItemPrice;
    int qtBasketItem;
    int qtBasketItemAmount;

    public int getNbBasketItem() {
        return nbBasketItem;
    }

    public void setNbBasketItem(int nbBasketItem) {
        this.nbBasketItem = nbBasketItem;
    }

    public int getNbBasket() {
        return nbBasket;
    }

    public void setNbBasket(int nbBasket) {
        this.nbBasket = nbBasket;
    }

    public int getCnBasketItemOrder() {
        return cnBasketItemOrder;
    }

    public void setCnBasketItemOrder(int cnBasketItemOrder) {
        this.cnBasketItemOrder = cnBasketItemOrder;
    }

    public String getNoProduct() {
        return noProduct;
    }

    public void setNoProduct(String noProduct) {
        this.noProduct = noProduct;
    }

    public String getNoUser() {
        return noUser;
    }

    public void setNoUser(String noUser) {
        this.noUser = noUser;
    }

    public int getQtBasketItemPrice() {
        return qtBasketItemPrice;
    }

    public void setQtBasketItemPrice(int qtBasketItemPrice) {
        this.qtBasketItemPrice = qtBasketItemPrice;
    }

    public int getQtBasketItem() {
        return qtBasketItem;
    }

    public void setQtBasketItem(int qtBasketItem) {
        this.qtBasketItem = qtBasketItem;
    }

    public int getQtBasketItemAmount() {
        return qtBasketItemAmount;
    }

    public void setQtBasketItemAmount(int qtBasketItemAmount) {
        this.qtBasketItemAmount = qtBasketItemAmount;
    }

    public void checkBasketItem(Connection connection, String nowUserNo, int nowBasketNo) {
        String checking = "select nb_basket_item, nb_basket, no_product, qt_basket_item " +
                "from tb_basket_item where nb_basket = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(checking);
            pstmt.setInt(1, nowBasketNo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("------------------------------------------------");
                System.out.println("장바구니 품목 식별번호: " + rs.getInt(1) +
                        "\t장바구니 식별번호: " + rs.getInt(2) +
                        "\t상품 코드: " + rs.getString(3) +
                        "\t장바구니 품목 수량: " + rs.getInt(4));
            }
            System.out.println();
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertIntoBasketItem(Connection connection, String nowUserNo, int nowBasketNo, String nowProductNo, int qtBasketItem) {
        String checkReady = "select * from TB_BASKET_ITEM where no_user = ?";
        int alreadyIn = 1;
        try {
            PreparedStatement pstmt = connection.prepareStatement(checkReady);
            pstmt.setString(1, nowUserNo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                alreadyIn++;
            }
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String insertInto = "insert into TB_BASKET_ITEM(nb_basket_item, nb_basket, cn_basket_item_order, no_product, no_user, qt_basket_item_price, qt_basket_item, qt_basket_item_amount) " +
                "values(seq_tb_basket_item.nextval, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(insertInto);
            pstmt.setInt(1, nowBasketNo);
            pstmt.setInt(2, alreadyIn);
            pstmt.setString(3, nowProductNo);
            pstmt.setString(4, nowUserNo);

            int getting = 0;
            try {
                String getPrice = "select qt_sale_price from tb_product where no_product=? ";
                PreparedStatement getP = connection.prepareStatement(getPrice);
                getP.setString(1, nowProductNo);
                ResultSet rs = getP.executeQuery();
                while (rs.next()) {
                    getting = rs.getInt(1);
                }
                try {
                    getP.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            pstmt.setInt(5, getting);
            pstmt.setInt(6, qtBasketItem);
            pstmt.setInt(7, qtBasketItem * getting);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + "개 데이터가 tb_basket_item에 추가");

            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromBasketItem(Connection connection, int nowBasketNo, String deleteTarget) {
        String deleting = "delete from tb_basket_item where no_product = ? and nb_basket = ? ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(deleting);
            pstmt.setString(1, deleteTarget);
            pstmt.setInt(2, nowBasketNo);
            pstmt.executeUpdate();
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("tb_basket_item 에서 " + deleteTarget + " 이 삭제되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

package java_final_user_proc_2460340001_김동현.ecommerce;

import java_final_user_proc_2460340001_김동현.user.TbUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TbBasket {
    int nbBasket;
    String noUser;
    int qtBasketAmount;

    public int getNbBasket() {
        return nbBasket;
    }

    public void setNbBasket(int nbBasket) {
        this.nbBasket = nbBasket;
    }

    public String getNoUser() {
        return noUser;
    }

    public void setNoUser(String noUser) {
        this.noUser = noUser;
    }

    public int getQtBasketAmount() {
        return qtBasketAmount;
    }

    public void setQtBasketAmount(int qtBasketAmount) {
        this.qtBasketAmount = qtBasketAmount;
    }

    public int createBasket(Connection connection, TbUser tbUser, TbBasketItem tbBasketItem) {
        String createBasket = "insert into tb_basket(nb_basket, no_user, qt_basket_amount) " +
                "values(seq_tb_basket.nextval, ?, 0)";
        int nowBasketNo = 0;

        try {
            PreparedStatement pstmt = connection.prepareStatement(createBasket);
            pstmt.setString(1, tbUser.getNoUser());
            int rows = pstmt.executeUpdate();
//            System.out.println(rows + "개 basket을 추가했습니다.");

            String getBasketNo = "select nb_basket from tb_basket where no_user=? ";
            pstmt = connection.prepareStatement(getBasketNo);
            pstmt.setString(1, tbUser.getNoUser());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                nowBasketNo = rs.getInt(1);
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
            tbBasketItem.setNbBasketItem(nowBasketNo);
            return nowBasketNo;
        }
    }

    public void calculateBasket(Connection connection, String nowUserNo, int nowBasketNo) {
        int total = 0;
        try {
            String calSql = "select qt_basket_item_amount from TB_BASKET_ITEM where nb_basket = ?";
            PreparedStatement pstmt = connection.prepareStatement(calSql);
            pstmt.setInt(1, nowBasketNo);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                total += rs.getInt(1);
            }
            try {
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String updateBasket = "update tb_basket set qt_basket_amount =? where nb_basket = ? ";
            PreparedStatement pstmt = connection.prepareStatement(updateBasket);
            pstmt.setInt(1, total);
            pstmt.setInt(2, nowBasketNo);
            int rows = pstmt.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }



    }

}

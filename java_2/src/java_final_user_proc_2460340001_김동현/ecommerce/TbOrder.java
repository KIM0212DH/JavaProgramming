package java_final_user_proc_2460340001_김동현.ecommerce;

import java_final_user_proc_2460340001_김동현.user.TbUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TbOrder {
    String idOrder;
    String noUser;
    int qtOrderAmount;
    int qtDeliMoney;
    int qtDeliPeriod;
    String nmOrderPerson;
    String daOrder;
    String stOrder;

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getNoUser() {
        return noUser;
    }

    public void setNoUser(String noUser) {
        this.noUser = noUser;
    }

    public int getQtOrderAmount() {
        return qtOrderAmount;
    }

    public void setQtOrderAmount(int qtOrderAmount) {
        this.qtOrderAmount = qtOrderAmount;
    }

    public int getQtDeliMoney() {
        return qtDeliMoney;
    }

    public void setQtDeliMoney(int qtDeliMoney) {
        this.qtDeliMoney = qtDeliMoney;
    }

    public int getQtDeliPeriod() {
        return qtDeliPeriod;
    }

    public void setQtDeliPeriod(int qtDeliPeriod) {
        this.qtDeliPeriod = qtDeliPeriod;
    }

    public String getNmOrderPerson() {
        return nmOrderPerson;
    }

    public void setNmOrderPerson(String nmOrderPerson) {
        this.nmOrderPerson = nmOrderPerson;
    }

    public String getDaOrder() {
        return daOrder;
    }

    public void setDaOrder(String daOrder) {
        this.daOrder = daOrder;
    }

    public String getStOrder() {
        return stOrder;
    }

    public void setStOrder(String stOrder) {
        this.stOrder = stOrder;
    }

    public ArrayList<String> getOrderProductList(Connection connection, String nowUserNo) {
        ArrayList<String> orderProductList = new ArrayList<>();
        String getting = "select no_product from tb_basket_item where no_user = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(getting);
            pstmt.setString(1, nowUserNo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                orderProductList.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return orderProductList;
        }
    }

    public int getDeliFee(Connection connection, String nowProduct) {
        String getting = "select qt_delivery_fee from tb_product where no_product=?";
        int deliFee = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(getting);
            pstmt.setString(1, nowProduct);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                deliFee = rs.getInt(1);
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
            return deliFee;
        }
    }

    public int getOrderAmount(Connection connection, String nowProduct, int nowBasketNo) {
        int totalPrice = 0;
        String getting = "select qt_basket_item_amount from tb_basket_item " +
                "where no_product=? and nb_basket = ? ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(getting);
            pstmt.setString(1, nowProduct);
            pstmt.setInt(2, nowBasketNo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                totalPrice += rs.getInt(1);
            }
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println(totalPrice);

            return totalPrice;
        }
    }

    public String ordering(Connection connection, TbUser tbUser, String orderProduct, int orderAmount, int deliveryFee, String orderPerson) {
        int productPrice = 0;
        String gettingProductPrice = "select qt_sale_price from tb_product where no_product=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(gettingProductPrice);
            pstmt.setString(1, orderProduct);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                productPrice = rs.getInt(1);
            }
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String orderingStr = "insert into tb_order(id_order, no_user, qt_order_amount, qt_deli_money, qt_deli_period, nm_order_person, da_order, st_order) " +
                "values('OR'||LPAD(seq_tb_order.nextval,7,'0'), ?, ?,?,0,?,SYSDATE,'10')";
        try {
            PreparedStatement pstmt = connection.prepareStatement(orderingStr);
            pstmt.setString(1, tbUser.getNoUser());
            pstmt.setInt(2, orderAmount * productPrice); // 개수 * 가격
            pstmt.setInt(3, deliveryFee);
            pstmt.setString(4, orderPerson);
            int rows = pstmt.executeUpdate();
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String orderSeq = "";
        String getSeqSql = "select id_order from tb_order where no_user=?";
        try {
            PreparedStatement pstmt2 = connection.prepareStatement(getSeqSql);
            pstmt2.setString(1, tbUser.getNoUser());
            ResultSet rs = pstmt2.executeQuery();
            while (rs.next()) {
                orderSeq = rs.getString(1);
            }
            try {
                pstmt2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return orderSeq;
        }
    }

    public String orderingBasket(Connection connection, TbUser tbUser, int totalDeliverFee, int totalPrice) {
        String inserting = "insert into tb_order(id_order, no_user, qt_order_amount, qt_deli_money, qt_deli_period, nm_order_person) " +
                "values('OR'||LPAD(seq_tb_order.nextval,7,'0'), ?, ?,?,0,?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(inserting);
            pstmt.setString(1, tbUser.getNoUser());
            pstmt.setInt(2, totalPrice);
            pstmt.setInt(3, totalDeliverFee);
            pstmt.setString(4, tbUser.getNmUser());
            int rows = pstmt.executeUpdate();
            try {
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        String orderSeq = "";
        String getSeqSql = "select id_order from tb_order where no_user=?";
        try {
            PreparedStatement pstmt2 = connection.prepareStatement(getSeqSql);
            pstmt2.setString(1, tbUser.getNoUser());
            ResultSet rs = pstmt2.executeQuery();
            while (rs.next()) {
                orderSeq = rs.getString(1);
            }
            try {
                pstmt2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return orderSeq;
        }
    }



}

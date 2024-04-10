package java_final_user_proc_2460340001_김동현.ecommerce;

import java_final_user_proc_2460340001_김동현.user.TbUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TbOrderItem {
    String idOrderItem;
    String idOrder;
    int cnOrderItem;
    String noProduct;
    String noUser;
    int qtUnitPrice;
    int qtOrderItem;
    int qtOrderItemAmount;
    int qtOrderItemDeliveryFee;

    public String getIdOrderItem() {
        return idOrderItem;
    }

    public void setIdOrderItem(String idOrderItem) {
        this.idOrderItem = idOrderItem;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public int getCnOrderItem() {
        return cnOrderItem;
    }

    public void setCnOrderItem(int cnOrderItem) {
        this.cnOrderItem = cnOrderItem;
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

    public int getQtUnitPrice() {
        return qtUnitPrice;
    }

    public void setQtUnitPrice(int qtUnitPrice) {
        this.qtUnitPrice = qtUnitPrice;
    }

    public int getQtOrderItem() {
        return qtOrderItem;
    }

    public void setQtOrderItem(int qtOrderItem) {
        this.qtOrderItem = qtOrderItem;
    }

    public int getQtOrderItemAmount() {
        return qtOrderItemAmount;
    }

    public void setQtOrderItemAmount(int qtOrderItemAmount) {
        this.qtOrderItemAmount = qtOrderItemAmount;
    }

    public int getQtOrderItemDeliveryFee() {
        return qtOrderItemDeliveryFee;
    }

    public void setQtOrderItemDeliveryFee(int qtOrderItemDeliveryFee) {
        this.qtOrderItemDeliveryFee = qtOrderItemDeliveryFee;
    }

    public void orderingItem(Connection connection, String nowSeq, String orderProduct, TbUser tbUser, int orderAmount, int deliveryFee) {
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
        String checkReady = "select * from TB_ORDER_ITEM where no_user = ?";
        int alreadyIn = 1;
        try {
            PreparedStatement pstmt = connection.prepareStatement(checkReady);
            pstmt.setString(1, tbUser.getNoUser());
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

        String insertOrderItem = "insert into tb_order_item(id_order_item, " +
                "id_order, cn_order_item, " +
                "no_product, no_user, qt_unit_price, " +
                "qt_order_item, qt_order_item_amount, qt_order_item_delivery_fee) " +
                "values('OT'||LPAD(seq_tb_order_item.nextval,7,'0'),?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(insertOrderItem);
            pstmt.setString(1, nowSeq);
            pstmt.setInt(2, alreadyIn);
            pstmt.setString(3, orderProduct);
            pstmt.setString(4, tbUser.getNoUser());
            pstmt.setInt(5, productPrice);
            pstmt.setInt(6, orderAmount);
            pstmt.setInt(7, productPrice * orderAmount);
            pstmt.setInt(8, deliveryFee);
            int rows = pstmt.executeUpdate();
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void orderingBasketItem(Connection connection, String nowSeq, String orderProduct, TbUser tbUser, int nowBasketNum) {
        int orderAmount = 0;
        int deliveryFee = 0;
        String getOrderAmount = "select qt_basket_item " +
                "from tb_basket_item " +
                "where no_user = ? and nb_basket = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(getOrderAmount);
            pstmt.setString(1, tbUser.getNoUser());
            pstmt.setInt(2, nowBasketNum);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                orderAmount = rs.getInt(1);
            }
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String getDF = "select qt_delivery_fee " +
                "from tb_product " +
                "where no_product = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(getDF);
            pstmt.setString(1, orderProduct);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                deliveryFee = rs.getInt(1);
            }
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String checkReady = "select * from TB_ORDER_ITEM where no_user = ?";
        int alreadyIn = 1;
        try {
            PreparedStatement pstmt = connection.prepareStatement(checkReady);
            pstmt.setString(1, tbUser.getNoUser());
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

        String insertOrderItemSql = "insert into tb_order_item(id_order_item, id_order, cn_order_item, no_product, no_user,qt_unit_price, qt_order_item, qt_order_item_amount, qt_order_item_delivery_fee) values('OT'||LPAD(seq_tb_order_item.nextval,7,'0'),?,?,?,?,?,?,?,?)";
//                "values('OT'||LPAD(SEQ_TB_ORDER_ITEM.nextval,7,'0'),?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(insertOrderItemSql);
            pstmt.setString(1, nowSeq);
            pstmt.setInt(2, alreadyIn);
            pstmt.setString(3, orderProduct);
            pstmt.setString(4, tbUser.getNoUser());
            pstmt.setInt(5, productPrice);
            pstmt.setInt(6, orderAmount);
            pstmt.setInt(7, productPrice * orderAmount);
            pstmt.setInt(8, deliveryFee);
            int rows = pstmt.executeUpdate();
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String deleteFromOrderItem = "delete from tb_basket_item where nb_basket = ? and no_user = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(deleteFromOrderItem);
            pstmt.setInt(1, nowBasketNum);
            pstmt.setString(2, tbUser.getNoUser());
            int rows = pstmt.executeUpdate();
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        String deleteFromOrder = "delete from tb_basket where nb_basket = ? and no_user = ?";
//        try {
//            PreparedStatement pstmt = connection.prepareStatement(deleteFromOrder);
//            pstmt.setInt(1, nowBasketNum);
//            pstmt.setString(2, tbUser.getNoUser());
//            int rows = pstmt.executeUpdate();
//            try {
//                pstmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }

}

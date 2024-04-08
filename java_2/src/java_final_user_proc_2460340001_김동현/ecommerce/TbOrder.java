package java_final_user_proc_2460340001_김동현.ecommerce;

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
}

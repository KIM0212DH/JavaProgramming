package kr.co.javaex.ex15.java_15_1_ex1_starcaroptionexam;

public class StarCarLowGrade extends StarCar {
    StarCarLowGrade() {
        super(CarSpecs.colorLow, CarSpecs.tireLow, CarSpecs.displacementLow, CarSpecs.handleLow);
    }

    @Override
    void getSpec() {
        if (this.displacement > CarSpecs.standard) {
            this.tax = CarSpecs.taxHigh;
        } else {
            this.tax = CarSpecs.taxLow;
        }
        System.out.println("[StarCar 저사양 옵션 및 세금]"
                +"\n----------------------------"
                +"\n색상\t\t\t:" + this.color
                + "\n타이어\t\t:" + this.tire
                + "\n배기량\t\t:" + this.displacement
                + "\n핸들\t\t\t:" + this.handle
                + "\n세금\t\t\t:" + this.tax);
    }
}

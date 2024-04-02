package kr.co.javaex.ex13;

public class AnimalEx {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.sound();

        Animal animalDog = dog; // 자동 타입 변환
        animalDog.sound();


        Animal animal = new Animal();
        animal.sound();


        Animal animalCat = new Cat();   // 자동 타입 변환 후, 메모리 공간 확보
        Cat cat = (Cat) animalCat;  // 강제 타입 변환
        cat.sound();
    }

}

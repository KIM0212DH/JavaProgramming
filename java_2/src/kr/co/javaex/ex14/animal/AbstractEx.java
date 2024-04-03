package kr.co.javaex.ex14.animal;


public class AbstractEx {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.sound();

        Cat cat = new Cat();
        cat.sound();

        Animal animal = new Animal() {
            @Override
            void sound() {
                System.out.println("울음소리");
            }
        };
        animal.sound();

        Animal aniCat = new Cat();
        aniCat.sound();

        Animal aniDog = new Dog();
        aniDog.sound();


    }
}

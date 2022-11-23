package m_future_13.supplement;

import lombok.Data;

import java.util.Optional;

public class Optional_03 {
    public static void main(String[] args) {
        Person p = new Person();
        Car c = new Car();
        Insurance i = new Insurance();

        //Optional.of()表示对象不能为null
        i.setName("insurance");
        c.setInsurance(Optional.of(i));
        p.setCar(Optional.of(c));

        String cin = getCin(p);
        System.out.println(cin);
    }

    static String getCin(Person p){
        //可以通过get方法从Optional中取出值
//        return p.getCar().get().getInsurance().get().getName();
        return p.getCar().flatMap(Car::getInsurance).get().getName();
    }
}

@Data
class Person{
    //有的人有车，也可能没有车，所以这里用Optional来修饰
    private Optional<Car> car;
}
@Data
class Car{
    //有的车上了保险，也有可能没有上，所以这里使用Optional修饰
    private Optional<Insurance> insurance;
}
@Data
class Insurance{
    private String name;
}
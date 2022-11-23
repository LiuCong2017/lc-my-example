package m_future_13.supplement;

import java.util.Optional;

/**
 * Optional包含有可选值的包装类，这意味着 Optional 类既可以含有对象也可以为空
 */
public class Optional_01 {
    public static void main(String[] args) {
//        Optional<Object> optional = Optional.empty();
//        optional.get();
//        optional.isPresent();
//        optional.orElse();
//        optional.filter();
//        optional.map();

        Optional<ObjectDemo> obj = Optional.ofNullable(new ObjectDemo());
        obj.orElse(null);
        obj.orElseGet(null);
        obj.filter(null);
        obj.map(null);

    }
}

class ObjectDemo{

}
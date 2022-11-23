package m_future_13.supplement;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Supplier接口是一个供给型的接口，本质就是一个容器，
 * 可以用来存储数据(或者是产生数据的规则)，
 * 然后可以供其他方法使用的这么一个接口。
 */
public class SupplierConsumer {
    //求数组最大元素
    static int getMax(Supplier<Integer> supplier){
        return supplier.get();
    }

    public static void main(String[] args) {
        int[] arr = {12,4,65,2,32};
        int result = getMax(()->{
           int max = 0;
            for (int j : arr) {
                if (max < j) max = j;
            }
           return max;
        });

        System.out.println(result);
    }

}

/**
 * Supplier接口(是一个函数接口,可以传递Lambda)被称之为生产型接口，
 * 指定接口的泛型是什么类型，那么接口中的get方法就会生产什么类型的数据
 */
class SupplierTest{
    String s = "str";
    SupplierTest(){
        System.out.println(s);
    }

    public static void main(String[] args) {
        // 创建Supplier容器，声明为TestSupplier类型
        Supplier<SupplierTest> supplier = SupplierTest::new;
        // 调用get()方法时,才调用对象的构造方法，创建对象实列
        supplier.get();
    }

}

class ConsumerTest{
    public static void main(String[] args) {
        // 创建两个容器对象
        Consumer<String> consumer1 = s -> System.out.println("hello 1 :" + s);
        Consumer<String> consumer2 = s -> System.out.println("hello 2 :" + s);

        //  先执行 consumer2.accept() , 如果没有异常再执行 consumer1.accept()
        consumer2.andThen(consumer1).accept("consumer");
    }
}
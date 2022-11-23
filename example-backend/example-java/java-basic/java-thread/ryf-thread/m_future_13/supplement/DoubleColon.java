package m_future_13.supplement;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * ::的使用
 * 双冒号（::）运算符在Java 8中被用作方法引用（method reference）
 */
public class DoubleColon extends BaseColon{
    public DoubleColon(){}
    public DoubleColon(String str){
    }

    @Test
    public void test(){
        List<String> list = Arrays.asList("a","b","c");
        //静态方法引用 ClassName::methodName
        list.forEach(DoubleColon::printStatic);
        //实例方法引用 instanceRef::methodName
        list.forEach(new DoubleColon()::printInstance);
        //引用父类实例方法
        list.forEach(super::printFather);
        //类构造器引用
        ColonNoParam cnp = DoubleColon::new; //无参构造引用
        ColonParam cp = DoubleColon::new; //有参构造引用
        DoubleColon bean1 = cnp.create();
        DoubleColon bean2 = cp.create("string");
        //数组构造器引用
        Function<Integer,DoubleColon[]> function = DoubleColon[]::new;
        DoubleColon[] arr = function.apply(4);

        ColonFunction<Integer,DoubleColon[]> colonFunction = DoubleColon[]::new;
        DoubleColon[] colonArr = colonFunction.apply(5);
    }
    static void printStatic(String Item){
        System.out.println(Item);
    }
    void printInstance(String Item){
        System.out.println(Item);
    }

}

class BaseColon{
    public void printFather(String Item){
        System.out.println(Item);
    }
}

interface ColonNoParam{
    DoubleColon create(); //不带参数
}
interface ColonParam{
    DoubleColon create(String s); //不带参数
}

//自定义函数式接口
@FunctionalInterface
interface ColonFunction<A,T>{
    T apply(A a);
}
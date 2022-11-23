package m_future_13.supplement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 避免使用isPresent()和get()方法，尽量多使用map()、filter()、orElse()等方法来发挥Optional的作用
 */
public class Optional_02 {

    public List<Student> initData() {
        Student s1 = new Student("张三", 19, 80);
        Student s2 = new Student("李四", 19, 50);
        Student s3 = new Student("王五", 23, null);
        Student s4 = new Student("赵六", 16, 90);
        Student s5 = new Student("钱七", 18, 99);
        Student s6 = new Student("孙八", 20, 40);
        Student s7 = new Student("吴九", 21, 88);
        return Arrays.asList(s1, s2, s3, s4, s5, s6, s7);
    }

    @Test
    public void useOptional(){
        /**
         * 学校想从一批学生中，选出年龄大于等于18，参加过考试并且成绩大于80的人去参加比赛。
         */
        List<Student> studentList = initData();
        for (Student student:studentList){
            Optional<Student> studentOptional = Optional.of(student);
//            assert(!studentOptional.isPresent());
            /**
             * filter: 过滤值，返回测试结果为true的值，如果测试结果为 false，会返回一个空的Optional
             * map: 接收函数参数，将返回的值包装在Optional中
             * orElse: 如果有值则返回该值，否则返回传递给它的参数值
             */
            Integer score = studentOptional.filter(s->s.getAge()>=18).map(Student::getScore).orElse(0);

            if (score>80) System.out.println(student.getName());
        }
    }

}


@Data
@NoArgsConstructor
@AllArgsConstructor
class Student {
    private String name;
    private int age;
    private Integer score;

    /**
     * 这是一个获取学生姓名的方法，方法入参为一个Student对象，为了防止student对象为null， 做了防御性检查：如果值为null，返回"Unkown"。
     */
    public static String returnName(Student student){
        //orElse()方法功能比较简单，即如果包装对象值非空，返回包装对象值，否则返回入参other的值（默认值）
        return Optional.ofNullable(student).map(Student::getName).orElse("Unknown");
    }

    /**
     *     ifPresent()方法接受一个Consumer对象（消费函数），如果包装对象的值非空，运行Consumer对象的accept()方法
     */
    public static void printName(Student student)
    {
        Optional.ofNullable(student).ifPresent(u ->  System.out.println("The student name is : " + u.getName()));
    }

    /**
     * filter()方法接受参数为Predicate对象，用于对Optional对象进行过滤，如果符合Predicate的条件，返回Optional对象本身，否则返回一个空的Optional对象
     */
    public static void filterAge(Student student)
    {
        Optional.ofNullable(student).filter( u -> u.getAge() > 18).ifPresent(u ->  System.out.println("The student age is more than 18."));
    }

    /**
     * map()方法的参数为Function（函数式接口）对象，map()方法将Optional中的包装对象用Function函数进行运算，并包装成新的Optional对象（包装对象的类型可能改变）
     */
    public static Optional<Integer> getAge(Student student)
    {
        //先用ofNullable()方法构造一个Optional<Student>对象，然后用map()计算学生的年龄，返回Optional<Integer>对象（如果student为null, 返回map()方法返回一个空的Optinal对象）
        return Optional.ofNullable(student).map(u -> u.getAge());
    }

    /**
     * 跟map()方法不同的是，入参Function函数的返回值类型为Optional<U>类型，而不是U类型，这样flatMap()能将一个二维的Optional对象映射成一个一维的对象
     */
    public static Optional<Integer> getFlatAge(Student student)
    {
        return Optional.ofNullable(student).flatMap(u -> Optional.ofNullable(u.getAge()));
    }

    /**
     * orElseGet()方法与orElse()方法类似，区别在于orElseGet()方法的入参为一个Supplier对象，用Supplier对象的get()方法的返回值作为默认值
     */
    public static String getGender(Student student)
    {
        return Optional.ofNullable(student).map(Student::getGender).orElseGet(() -> "Unkown");
    }

    /**
     * orElseThrow()方法其实与orElseGet()方法非常相似了，入参都是Supplier对象，只不过orElseThrow()的Supplier对象必须返回一个Throwable异常，并在orElseThrow()中将异常抛出
     * orElseThrow()方法适用于包装对象值为空时需要抛出特定异常的场景。
     */
    public static String getGender1(Student student)
    {
        return Optional.ofNullable(student).map(Student::getGender).orElseThrow(() -> new RuntimeException("Unkown"));
    }
}
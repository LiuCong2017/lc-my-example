package m_future_13.supplement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalTest {

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
}
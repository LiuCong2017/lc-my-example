package fundament_03;

/**
 * exist bugs
 */
public class ThreadSync_02 {
    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[]{
            new AddStudentThread(),
            new DecStudentThread(),
//            new AddTeacherThread(),
//            new DecTeacherThread(),
        };

        for (Thread t:ts){
            t.start();
        }
        for (Thread t:ts){
            t.join();
        }
        System.out.println(Counter2.get());
//        System.out.println(Counter2.teacherCount);
    }
}

class Counter2{
//    public static final Object lockStudent = new Object();
//    public static final Object lockTeacher = new Object();
    private static int studentCount = 0;
    private static int teacherCount = 0;

    public static void addStudentCount(){
//        synchronized (this){
            studentCount += 1;
//        }
    }

    public static void decStudentCount(){
//        synchronized (this){
            studentCount -= 1;
//        }
    }

    public static int get(){
        return studentCount;
    }

}

class AddStudentThread extends Thread{
    public void run(){
        for (int i=0;i<10000;i++){
//            synchronized (Counter2.lockStudent){
//                Counter2.studentCount += 1;
//            }
            Counter2.addStudentCount();
        }
    }
}

class DecStudentThread extends Thread{
    public void run(){
        for (int i=0;i<10000;i++){
//            synchronized (Counter2.lockStudent){
//                Counter2.teacherCount -= 1;
//            }
            Counter2.decStudentCount();
        }
    }
}

//class AddTeacherThread extends Thread {
//    public void run() {
//        for (int i=0; i<10000; i++) {
//            synchronized(Counter2.lockTeacher) {
//                Counter2.teacherCount += 1;
//            }
//        }
//    }
//}
//
//class DecTeacherThread extends Thread {
//    public void run() {
//        for (int i=0; i<10000; i++) {
//            synchronized(Counter2.lockTeacher) {
//                Counter2.teacherCount -= 1;
//            }
//        }
//    }
//}
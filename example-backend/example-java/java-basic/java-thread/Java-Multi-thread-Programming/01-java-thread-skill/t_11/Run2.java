package t_11;

public class Run2 {
    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        System.out.println("is interrupt1? = "+Thread.interrupted());
        /**
         * if invoke twice, the second invoke will return false,
         * because the first invoke has erased the status flag into false
         */
        System.out.println("is interrupt2? = "+Thread.interrupted());
        System.out.println("end!");
    }
}

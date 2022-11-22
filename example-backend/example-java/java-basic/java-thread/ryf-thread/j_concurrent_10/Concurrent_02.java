package j_concurrent_10;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用java.util.concurrent包提供的线程安全的并发集合可以大大简化多线程编程：
 * 多线程同时读写并发集合是安全的；
 * 尽量使用Java标准库提供的并发集合，避免自己编写同步代码。
 */
public class Concurrent_02 {
    public static void main(String[] args) {
        /**
         * 因为所有的同步和加锁的逻辑都在集合内部实现，
         * 对外部调用者来说，只需要正常按接口引用，
         * 其他代码和原来的非线程安全代码完全一样。
         * 即当我们需要多线程访问时，把：non-thread-safe -> thread-safe
         */
        //使用这些并发集合与使用非线程安全的集合类完全相同。我们以ConcurrentHashMap为例：
        Map<String,String> map = new ConcurrentHashMap<>();
        // 在不同的线程读写:
        map.put("A", "1");
        map.put("B", "2");
        map.get("A");
    }
}

/**
 * 因为BlockingQueue非常有用，所以我们不必自己编写，
 * 可以直接使用Java标准库的java.util.concurrent包提供的线程安全的集合：
 * ArrayBlockingQueue。
 *
 * 除了BlockingQueue外，针对List、Map、Set、Deque等，
 * java.util.concurrent包也提供了对应的并发集合类。
 * 我们归纳一下：
 * interface	non-thread-safe	        thread-safe
 * List	        ArrayList	            CopyOnWriteArrayList
 * Map	        HashMap	                ConcurrentHashMap
 * Set	        HashSet/TreeSet	        CopyOnWriteArraySet
 * Queue        ArrayDeque/LinkedList	ArrayBlockingQueue/LinkedBlockingQueue
 * Deque	    ArrayDeque/LinkedList	LinkedBlockingDeque
 */

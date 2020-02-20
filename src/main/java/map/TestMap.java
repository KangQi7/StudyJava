package map;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TestMap {
    public static Map<Long, Integer> testMapPut() {
        ConcurrentHashMap<Long, Integer> pcRankMap = new ConcurrentHashMap<>();

        ExecutorService pool = Executors.newFixedThreadPool(3);
        Integer pcPageNo = 30;
        AtomicInteger pcPage = new AtomicInteger(1);
        while (pcPage.intValue() <= pcPageNo) {
            int finalPage = pcPage.intValue();
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0, length = 30; i < length; i++) {
                            int rank = ((finalPage - 1) * 30 + i + 1);
                            Integer _rank = pcRankMap.get(100000L);
                            if (_rank != null){
                                pcRankMap.put(new Long((long)rank), _rank < rank ? rank : _rank);
                            }
                            else{
                                pcRankMap.put(new Long((long)rank), rank);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            pcPage.getAndAdd(1);
        }

        // 关闭线程池
        pool.shutdown();
        while (true) {
            if (pool.isTerminated()) {
//                System.out.println("所有的子线程都结束了！");
                break;
            }
        }

        Map<Long , Integer> rankMap = pcRankMap;

        Integer r1 = rankMap.get(901L);
        Integer r4 = rankMap.get(1001L);
        Integer r2 = rankMap.get(300L);
        Integer r3 = rankMap.get(1L);

        return rankMap;
    }


}

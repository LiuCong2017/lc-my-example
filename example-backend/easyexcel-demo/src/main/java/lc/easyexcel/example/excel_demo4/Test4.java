package org.example.excel_demo4;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.merge.OnceAbsoluteMergeStrategy;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test4 {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        String fileName = "mergeWrite" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, DownloadDTO.class).sheet("sheet名称")
                .doWrite(getFruitData());
    }
    // 模拟从数据库读取需要下载的列表信息
    private static List<DownloadDTO> getFruitData() throws InvocationTargetException, IllegalAccessException {
        List<DownloadDTO> returnList = new ArrayList<>();
        DownloadDTO d1 = new DownloadDTO();
        d1.setCategory("水果");
        d1.setFruit("苹果");
        d1.setColor("红色");
        d1.setProduceDate(new Date());

        DownloadDTO d2 = new DownloadDTO();
        BeanUtils.copyProperties(d1, d2);
        d2.setColor("绿色");

        DownloadDTO d3 = new DownloadDTO();
        BeanUtils.copyProperties(d1, d3);
        d2.setColor("白色");

        DownloadDTO t1 = new DownloadDTO();
        t1.setCategory("水果");
        t1.setFruit("香蕉");
        t1.setColor("黄色");
        t1.setProduceDate(new Date());

        DownloadDTO t2 = new DownloadDTO();
        BeanUtils.copyProperties(t1, t2);
        t2.setColor("青色");

        returnList.add(d1);
        returnList.add(d2);
        returnList.add(d3);
        returnList.add(t1);
        returnList.add(t2);
        return returnList;
    }

    //-------------------------------------------------

    public void loopMergeStrategyTest() throws InvocationTargetException, IllegalAccessException {
        String fileName = "mergeWrite" + System.currentTimeMillis() + ".xlsx";
        // 将第一列的数据每隔两行进行合并
        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(2, 0);
        EasyExcel.write(fileName, DownloadDTO.class).registerWriteHandler(loopMergeStrategy)
                .sheet("sheet名称").doWrite(getFruitData());
    }

    //指定坐标范围，对固定区间进行合并
    public void onceMergeStrategyTest() throws InvocationTargetException, IllegalAccessException {
        String fileName = "mergeWrite" + System.currentTimeMillis() + ".xlsx";
        OnceAbsoluteMergeStrategy onceAbsoluteMergeStrategy = new OnceAbsoluteMergeStrategy(1, 3, 1, 1);
        EasyExcel.write(fileName, DownloadDTO.class).registerWriteHandler(onceAbsoluteMergeStrategy)
                .sheet("sheet名称").doWrite(getFruitData());
    }

//    //    一次使用多个合并策略
//    public void onceMergeStrategyTest() throws InvocationTargetException, IllegalAccessException {
//        String fileName = "mergeWrite" + System.currentTimeMillis() + ".xlsx";
//        OnceAbsoluteMergeStrategy onceAbsoluteMergeStrategy = new OnceAbsoluteMergeStrategy(1, 3, 1, 1);
//        OnceAbsoluteMergeStrategy onceAbsoluteMergeStrategy2 = new OnceAbsoluteMergeStrategy(4, 5, 1, 1);
//        EasyExcel.write(fileName, DownloadDTO.class)
//                .registerWriteHandler(onceAbsoluteMergeStrategy)
//                .registerWriteHandler(onceAbsoluteMergeStrategy2)
//                .sheet("sheet名称").doWrite(getFruitData());
//    }

    /**
     * 自定义实现合并策略
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void myMergeStrategyTest() throws InvocationTargetException, IllegalAccessException {
        String fileName = "mergeWrite" + System.currentTimeMillis() + ".xlsx";
        MyMergeStrategy myMergeStrategy = new MyMergeStrategy(getFruitData(), getGroupData());
        EasyExcel.write(fileName, DownloadDTO.class).registerWriteHandler(myMergeStrategy)
                .sheet("sheet名称").doWrite(getFruitData());
    }

    private List<Integer> getGroupData() {
        return null;
    }

}

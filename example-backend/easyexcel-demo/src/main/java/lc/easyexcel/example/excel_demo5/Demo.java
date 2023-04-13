package org.example.excel_demo5;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 合并单元格
 */
/**
 * 合并单元格
 * @date 01/31/2021 03:11
 */
public class Demo {

    /**
     * 合并单元格
     */
    public void testMyMergeWrite() {

        String fileName = "/Users/quanlinglong/Downloads/mergeDemo/" + "mymergeWrite" + System.currentTimeMillis() + ".xlsx";
        System.out.println("fileName = " + fileName);

        // 每隔2行合并一次，横向合并1列，从第0列开始
//        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(2, 1, 0);
        // 上面的等价于
//        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(2, 0);

        // 从第columnIndex列开始，向右合并（columnExtend-1）列
//        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(1, 2, 0);

        // 合并2行2列
        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(2, 2, 0);

//        EasyExcel.write(fileName, WriteDemo.DemoData.class)
//                .registerWriteHandler(loopMergeStrategy)
//                .sheet("aaa")
//                .doWrite(data());
    }

    private List<List<Object>> data() {
        List<List<Object>> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            result.add(Lists.newArrayList("标题" + i, new Date(), i * 0.1));
        }
        return result;
    }

}

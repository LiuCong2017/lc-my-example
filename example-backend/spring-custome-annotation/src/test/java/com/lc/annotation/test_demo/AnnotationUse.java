package com.lc.annotation.test_demo;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/11/23 09:13]
 */
@TestAnnotation(test="AnnotationUse")
public class AnnotationUse {

    @TestAnnotation(test="annotationUse",number = 566)
    public void annotationUse(){
        System.out.println("使用了自定义注解");
    }

}

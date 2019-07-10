package io.jboot.admin.base.util;

public class MathUtil {

    public static Integer getPage(Integer totalRows,Integer pageSize){
        if(totalRows%pageSize==0){
            return totalRows/pageSize;
        }else{
            return totalRows/pageSize+1;
        }
    }
}

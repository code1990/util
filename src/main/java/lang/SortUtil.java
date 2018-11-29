package lang;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

/**
 * @author issuser  定义常见的七种排序算法
 */
public class SortUtil {

    /**
     * 满足倒序的排序要求 添加倒转的方法
     *
     * @param array
     * @return array
     */
    public static int[] reverse(int[] array) {
        int[] resultArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            resultArray[resultArray.length - 1 - i] = array[i];
        }
        return resultArray;
    }




}

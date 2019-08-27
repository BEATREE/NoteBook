import java.util.*;

class BubbleSortAdvanced{

    // 实现排序的方法
    public static void sort(int array[]){
        for(int i = 0; i < array.length; i++){
            // 有序标记每一轮的标记都是 true， 用于改进普通的冒泡排序
            boolean isSorted = true;
            for(int j = 0; j < array.length - i - 1; j++){
                if(array[j] > array[j+1]){  // 如果元素大于右侧的元素，则调换位置
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;

                    isSorted = false;       // 因为有元素交换，所以不是有序的
                }
            }

            if(isSorted){
                break;
            }
        }
    }

    public static void main(String[] args){
        int[] array1 = {1, 2, 9, 5, 8, 6, 3, 7, 4, 0};
        sort(array1);                               // 数组在方法中是作为对象进行传递的
        System.out.println(Arrays.toString(array1));
        System.out.println("你好");
    }
}
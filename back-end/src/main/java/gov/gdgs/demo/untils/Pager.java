package gov.gdgs.demo.untils;

import java.util.Collections;
import java.util.List;

public class Pager<T> {  
	  
    /** 
     * 每页显示条数 
     */  
    private int pageSize;  
    /** 
     * 原集合 
     */  
    private List<T> data;  
  
    private Pager(List<T> data, int pageSize) {  
        if (data == null || data.isEmpty()) {  
            throw new IllegalArgumentException("data must be not empty!");  
        }  
  
        this.data = data;  
        this.pageSize = pageSize;  
    }  
  
    /** 
     * 创建分页器 
     * 
     * @param data 需要分页的数据 
     * @param pageSize 每页显示条数 
     * @param <T> 业务对象 
     * @return 分页器 
     */  
    public static <T> Pager<T> create(List<T> data, int pageSize) {  
        return new Pager<>(data, pageSize);  
    }  
  
    /** 
     * 得到分页后的数据 
     * 
     * @param pageNum 页码 
     * @return 分页后结果 
     */  
    public List<T> getPagedList(int pageNum) {  
        int fromIndex = (pageNum - 1) * pageSize;  
        if (fromIndex >= data.size()) {  
            return Collections.emptyList();  
        }  
  
        int toIndex = pageNum * pageSize;  
        if (toIndex >= data.size()) {  
            toIndex = data.size();  
        }  
        return data.subList(fromIndex, toIndex);  
    }  
  
    public int getPageSize() {  
        return pageSize;  
    }  
  
    public List<T> getData() {  
        return data;  
    }  
  /**
   * 
   * @使用方法
   */
  /**  public static void main(String[] args) {  
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};  
        List<Integer> list = Arrays.asList(array);  
  
        Pager<Integer> pager = Pager.create(list, 10);  
  
        List<Integer> page1 = pager.getPagedList(1);  
        System.out.println(page1);  
  
        List<Integer> page2 = pager.getPagedList(2);  
        System.out.println(page2);  
  
        List<Integer> page3 = pager.getPagedList(3);  
        System.out.println(page3);  
    }  */
}  
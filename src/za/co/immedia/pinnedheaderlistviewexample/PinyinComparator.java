/**
 *    Filename : Pinyin.java
 *    Author   : CX
 *    Date     : 2013-9-9
 *
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package za.co.immedia.pinnedheaderlistviewexample;

import java.util.Comparator;

/**
 * 根据sortName进行排序
 * @author CX
 *
 */
public class PinyinComparator implements Comparator<NameModel> {

    @Override
    public int compare(NameModel o1, NameModel o2) {
        // TODO Auto-generated method stub
        String py1 = o1.sortName;
        String py2 = o2.sortName;
        // 判断是否为空""
        if (isEmpty(py1) && isEmpty(py2)) return 0;
        if (isEmpty(py1)) return -1;
        if (isEmpty(py2)) return 1;
        String str1 = "";
        String str2 = "";
        try {
            str1 = ((o1.sortName).toUpperCase()).substring(0, 1);
            str2 = ((o2.sortName).toUpperCase()).substring(0, 1);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("某个str为\" \" 空");
        }
        return str1.compareTo(str2);
    }

    private boolean isEmpty(String str) {
        return "".equals(str.trim());
    }
}

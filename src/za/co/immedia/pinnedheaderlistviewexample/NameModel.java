package za.co.immedia.pinnedheaderlistviewexample;

/**
 * @author CX 处理文字的
 */
public class NameModel {

    
    
    /**
     * 本来的名字
     */
    public String name;

    /**
     * 排序用的名字
     */
    public String sortName;

    @Override
    public String toString() {
        return "Country [name=" + name + ", target=" + sortName + "]";
    }


}

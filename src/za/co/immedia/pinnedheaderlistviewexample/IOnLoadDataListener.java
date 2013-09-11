/**
 * Filename : IOnLoadDataListener.java Author : CX Date : 2013-9-9
 * 
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package za.co.immedia.pinnedheaderlistviewexample;


/**
 * @author CX
 * 
 */
public interface IOnLoadDataListener {

    public void onBeforeLoad();

    public void onAferLoad();

    public void onItemClick(int position);
    
    public void onSectionClick(String key);
    
    
}

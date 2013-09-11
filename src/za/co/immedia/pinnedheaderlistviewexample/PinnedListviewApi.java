/**
 * Filename : PinnedListviewApi.java Author : CX Date : 2013-9-9
 * 
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package za.co.immedia.pinnedheaderlistviewexample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;
import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView.OnItemClickListener;

import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;

/**
 * @author CX
 * 
 */
public class PinnedListviewApi {

    private HashMap<String, ArrayList<NameModel>> map = new HashMap<String, ArrayList<NameModel>>();
    private HashMap<String, Integer> alphaIndexer;// 保存每个索引在list中的位置【#-0，A-4，B-10】
    private String[] sections;// 每个分组的索引表【A,B,C,F...】
    private IOnLoadDataListener mOnLoadDataListener;
    PinnedHeaderListView listView;
    ArrayList<NameModel> list;

    public PinnedListviewApi(ArrayList<NameModel> list, PinnedHeaderListView listView,
            IOnLoadDataListener onLoadDataListener) {
        this.list = list;
        this.listView = listView;
        this.mOnLoadDataListener = onLoadDataListener;
        new LoadDataTask().execute(list);
    }



    class LoadDataTask extends AsyncTask<ArrayList<NameModel>, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mOnLoadDataListener.onBeforeLoad();
        }

        
        public int getPosition(int section ,int position){
            NameModel nameModel=map.get(sections[section]).get(position);
            return list.indexOf(nameModel);
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            TestSectionedAdapter sectionedAdapter = new TestSectionedAdapter(list, map, alphaIndexer, sections);
            listView.setAdapter(sectionedAdapter);
            mOnLoadDataListener.onAferLoad();
            
            listView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int section, int position, long id) {
                    mOnLoadDataListener.onItemClick(getPosition(section,position));
                }

                @Override
                public void onSectionClick(AdapterView<?> adapterView, View view, int section, long id) {
                    mOnLoadDataListener.onSectionClick(sections[section]);
                }
            });


     
        }


        @Override
        protected Void doInBackground(ArrayList<NameModel>... params) {
            initList(params[0]);
            return null;
        }
    }



    /**
     * 初始化索引列表，连带着初始化map
     * 
     * @param list
     */
    private void initIndex(ArrayList<NameModel> list) {
        this.alphaIndexer = new HashMap<String, Integer>();
        // 构建一个缓存列表
        ArrayList<NameModel> cacheList = new ArrayList<NameModel>();
        String cacheName = "";
        for (int i = 0; i < list.size(); i++) {
            String name = getAlpha(list.get(i).sortName);
            if (!alphaIndexer.containsKey(name)) {// 只记录在list中首次出现的位置
                alphaIndexer.put(name, i);
                // 不取第一个。map的原理是读到A之后，把所有A开发的都放到A列表里，所以要跳过第一个
                if (i != 0) {
                    map.put(cacheName, cacheList);
                    cacheList = new ArrayList<NameModel>();
                }
                cacheName = name;
            }
            cacheList.add(list.get(i));
            // 把最后一个列表加进去
            if (i == (list.size() - 1)) {
                if (!map.containsKey(cacheName)) {
                    map.put(cacheName, cacheList);
                }
            }
        }

        Set<String> sectionLetters = alphaIndexer.keySet();
        ArrayList<String> sectionList = new ArrayList<String>(sectionLetters);
        Collections.sort(sectionList);
        sections = new String[sectionList.size()];
        sectionList.toArray(sections);

    }


    public void logMap() {
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println("key  " + entry.getKey());
            System.out.println("value  " + map.get(entry.getKey()));
        }
    }

    private void initList(ArrayList<NameModel> list) {
        Collections.sort(list, new PinyinComparator());
        initIndex(list);
    }

    /**
     * 取得首字母
     * 
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        if (str == null) {
            return "#";
        }
        if (str.trim().length() == 0) {
            return "#";
        }
        char c = str.trim().substring(0, 1).charAt(0);
        // 正则表达式，判断首字母是否是英文字母
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase(); // 大写输出
        } else {
            return "#";
        }
    }

}

package za.co.immedia.pinnedheaderlistviewexample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import za.co.immedia.pinnedheaderlistview.SectionedBaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

/**
 * 一个继承于自定义的adapter
 * 
 * @author CX
 * 
 */
public class TestSectionedAdapter extends SectionedBaseAdapter implements SectionIndexer {


    ArrayList<NameModel> list = new ArrayList<NameModel>();
    private HashMap<String, ArrayList<NameModel>> map = new HashMap<String, ArrayList<NameModel>>();
    private HashMap<String, Integer> alphaIndexer;// 保存每个索引在list中的位置【#-0，A-4，B-10】
    private String[] sections;// 每个分组的索引表【A,B,C,F...】

    public TestSectionedAdapter(ArrayList<NameModel> list,HashMap<String, ArrayList<NameModel>> map,HashMap<String, Integer> alphaIndexer,String[] sections) {
        this.list = list;
        this.map=map;
        this.alphaIndexer=alphaIndexer;
        this.sections=sections;
      

    }

    @Override
    public Object getItem(int section, int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int section, int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getSectionCount() {
        return sections.length;
    }


    @Override
    public int getCountForSection(int section) {
        return map.get(sections[section]).size();
    }

    @Override
    public View getItemView(int section, int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflator =
                    (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_list_item);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_name.setText(map.get(sections[section]).get(position).name);
        return convertView;
    
//        LinearLayout layout = null;
//        if (convertView == null) {
//            LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            layout = (LinearLayout) inflator.inflate(R.layout.list_item, null);
//        } else {
//            layout = (LinearLayout) convertView;
//        }
//        ((TextView) layout.findViewById(R.id.tv_list_item)).setText(map.get(sections[section]).get(position).name);
//        return layout;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {

//        LinearLayout layout = null;
//        if (convertView == null) {
//            LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            layout = (LinearLayout) inflator.inflate(R.layout.header_item, null);
//        } else {
//            layout = (LinearLayout) convertView;
//        }
//        ((TextView) layout.findViewById(R.id.tv_header_item)).setText(sections[section]);
//        return layout;
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflator =
                    (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.header_item, null);
            holder = new ViewHolder();      
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_header_item);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_name.setText(sections[section]);
        return convertView;
    }


    private class ViewHolder {
        TextView tv_name;
    }

    @Override
    public int getPositionForSection(int section) {
        String later = sections[section];
        // alphaIndexer.get(later) 是获得这个item在列表里的位置。因为还有一个header的位置在，所以加上section
        return (alphaIndexer.get(later) + section);
    }

    @Override
    public Object[] getSections() {
        return sections;
    }


}

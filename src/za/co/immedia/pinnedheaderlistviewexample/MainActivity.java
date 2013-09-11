package za.co.immedia.pinnedheaderlistviewexample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;
import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView.OnItemClickListener;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {



    /**
     * 从arrays.xml里读测试数据，真实情况下不需要
     */
    private String[] mString = null;
    ArrayList<NameModel> list = new ArrayList<NameModel>();

    PinnedHeaderListView listView;

    ProgressDialog proDlg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        proDlg = new ProgressDialog(this);
        listView = (PinnedHeaderListView) findViewById(R.id.pinnedListView);

        // 从arrays.xml里读取测试数据
        mString = getResources().getStringArray(R.array.fl_search_keyword);

        // 处理测试数据，弄成ArrayList
        for (int i = 0; i < mString.length; i++) {
            NameModel nameModel = new NameModel();
            nameModel.name = mString[i];
            // 玩这些的目的就是要这个sortName，用于列表排序
            nameModel.sortName = ResourcesNameUtil.getSortKey(mString[i]);
            if (nameModel.sortName == null) {
                continue;
            }
            list.add(nameModel);
        }

        new PinnedListviewApi(list, listView, new IOnLoadDataListener() {

            @Override
            public void onBeforeLoad() {
                proDlg.show();
            }

            @Override
            public void onAferLoad() {
                proDlg.cancel();
            }

            @Override
            public void onItemClick(int position) {
                
                System.out.println("model "+list.get(position));
                
            }

            @Override
            public void onSectionClick(String key) {
                System.out.println("key "+key);
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}

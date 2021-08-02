package com.sm.android.locations.location.Activity.AddParam;

import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diycoder.library.listener.RecyclerTouchListener;
import com.sm.android.locations.location.Activity.AddParam.Adapter.TestBaseAdapter;
import com.sm.android.locations.location.Activity.AddParam.Adapter.TestData;
import com.sm.android.locations.location.Base.BaseActivity;
import com.sm.android.locations.location.R;
import com.sm.android.locations.location.Utils.OrmSqlLite.Bean.AddPararBean;
import com.sm.android.locations.location.Utils.OrmSqlLite.DBManagerAddParam;
import com.sm.android.locations.location.Utils.UtilsView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 参数列表
 */
public class ParamActivity extends BaseActivity implements View.OnClickListener {
    ImageView iv_findish;
    private RecyclerView recyclerView;
    private List<TestData> data = new ArrayList<>();
    private TestBaseAdapter mAdapter;
    private DBManagerAddParam dbManagerAddParam;
    List<AddPararBean> dataAll = null;


    TextView nullData;
    TestBaseAdapter.ITTestBaseAdapterCallBack itTestBaseAdapterCallBack = new TestBaseAdapter.ITTestBaseAdapterCallBack() {
        @Override
        public void Call(int id, boolean checkFlag, CheckBox checkBox, TestBaseAdapter.ItemViewHolder itemViewHolder) {
            Log.d("opjiohiho", "Call: " + id + "boolean---" + checkFlag);
            try {
                AddPararBean forid = dbManagerAddParam.forid(id);
                forid.setCheckbox(checkFlag);
                int i1 = dbManagerAddParam.updateCheck(forid);
                Log.d("nzqqqqqqqq", "Call: "+i1);



                List<AddPararBean> dataAll = dbManagerAddParam.getDataAll();
                List<AddPararBean> list = new ArrayList<>();
//                if (dataAll.size() > 0 && dataAll != null) {
//                    for (int i = 0; i < dataAll.size(); i++) {
//                        if (dataAll.get(i).isCheckbox() == true) {
//                            list.add(dataAll.get(i));
//                        }
//                    }
//                    if (list.size() > 0 && list != null) {
//                        if (list.size() > 20) {
//                            ToastUtils.showToast("大于20了");
//                        }
//                    } else {
//                        Log.d("nzqcheck", "Call:3 ");
////
//                    }
//                } else {
//                    Log.d("nzqcheck", "Call:4 ");
//                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void CallDele(int id, int position) {
            data.remove(position);
//            setadapter();
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            GridLayoutManager mLayoutManager = new GridLayoutManager(ParamActivity.this, 1);
            recyclerView.setLayoutManager(mLayoutManager);
//            recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 12, false));
            mAdapter = new TestBaseAdapter(ParamActivity.this, itTestBaseAdapterCallBack);
            mAdapter.setDataList(data);
            mAdapter.setHasMoreData(true);
            recyclerView.setAdapter(mAdapter);
            if (data.size() > 0) {
                nullData.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            } else {
                nullData.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        }
    };

    @Override
    protected void initQx() {
    }

    @Override
    protected void init() {

    }

    @Override
    protected void findViews() {
        ImageView iv_add = findViewById(R.id.iv_add);
        iv_add.setVisibility(View.VISIBLE);
        iv_add.setOnClickListener(this);
        nullData = findViewById(R.id.nullData);
        LinearLayout layout = findViewById(R.id.ll_tab);
        TextView title = findViewById(R.id.titles);
        ImageView imageView = findViewById(R.id.iv_menu);
        iv_findish = findViewById(R.id.iv_finish);
        iv_findish.setOnClickListener(this);
        UtilsView.setViewVisibility(this, layout, title, imageView, getResources().getString(R.string.param_activity), false, iv_findish, true);

        setadapter();
//        try {
//            dbManagerAddParam.deleteall();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        recyclerView.addOnScrollListener(scrollListener);
        //添加触摸监听
        RecyclerTouchListener onTouchListener = new RecyclerTouchListener(this, recyclerView);
        onTouchListener
                .setIndependentViews(R.id.rowButton)
//                .setViewsToFade(R.id.rowButton)
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {//item点击监听
//                        Toast.makeText(ParamActivity.this, "Row " + (position + 1) + " clicked!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {//button点击监听
//                        Toast.makeText(getApplicationContext(), "Button in row " + (position + 1) + " clicked!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setSwipeOptionViews(R.id.start, R.id.thumb, R.id.favorite)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {//侧拉出现的三个按钮监听事件
//                        TestData itemData = mAdapter.getItemData(position);//得到数据
//                        String message = "";
//                        if (viewID == R.id.start) {
//                            message += "收 藏";//未使用 并隐藏
//                        } else if (viewID == R.id.thumb) {//编辑
//                            message += "点 赞";
//                        } else if (viewID == R.id.favorite) {//删除
//                            message += "喜 欢";
//
//                            new CircleDialog.Builder()
//                                    .setTitle("")
//                                    .setText("确定要删除IMSI吗")
//                                    .setTitleColor(Color.parseColor("#00acff"))
//                                    .setNegative("确定", new Positiv(itemData.id, position))
//                                    .setPositive("取消", null)
//                                    .show(getSupportFragmentManager());
//                        }
//                        message += " position-> " + (position);
//
//                        Toast.makeText(getApplicationContext(), message + itemData.phone, Toast.LENGTH_SHORT).show();
                    }
                });
        recyclerView.addOnItemTouchListener(onTouchListener);
    }

    private void setadapter() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        try {
            dbManagerAddParam = new DBManagerAddParam(ParamActivity.this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {

            dataAll = dbManagerAddParam.getDataAll();
            Log.d("nzq", "findViews: " + dataAll);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (dataAll.size() > 0 && dataAll != null) {
            data.clear();
            nullData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            for (int i = 0; i < dataAll.size(); i++) {
                TestData testData = new TestData();
                testData.imsi = dataAll.get(i).getImsi();
                testData.name = dataAll.get(i).getName();
                testData.phone = dataAll.get(i).getPhone();
                testData.id = dataAll.get(i).getId();
                testData.yy = dataAll.get(i).getYy();
                testData.check = dataAll.get(i).isCheckbox();
                data.add(testData);
                Log.d("nzqqqqq", "setadapter: " + data);
            }
        } else {
            nullData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

        Log.d("nzqqqqqa", "setadapter: "+data.toString());
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 12, false));
        mAdapter = new TestBaseAdapter(ParamActivity.this, itTestBaseAdapterCallBack);
        mAdapter.setDataList(data);
        mAdapter.setHasMoreData(true);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    protected int getView() {
        return R.layout.activity_param;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setadapter();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                finish();
                break;

            case R.id.iv_add:
                startActivity(new Intent(ParamActivity.this, AddParamActivity.class));
                break;
        }
    }


}

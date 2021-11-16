package com.ams.amsvistara.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ams.amsvistara.R;
import com.ams.amsvistara.adapter.TagDialogListAdapter;
import com.ams.amsvistara.db.table.master.TagModels;
import com.ams.amsvistara.interfaces.TagDialogClickListener;
import com.ams.amsvistara.ui.activity.TaggingListActivity;

import java.util.ArrayList;

import static com.ams.amsvistara.constants.ConstantStr.TAG_MODEL_ID;

public class TagListPvDialog extends Dialog implements TagDialogClickListener {

    private RecyclerView list;
    private TextView txtTitle;
    //private EditText filterText = null;
    private TagDialogListAdapter adapter = null;
    private static final String TAG = "TagModels";
    private Context mContext;

    public TagListPvDialog(Context context, ArrayList<TagModels> tagModels) {
        super(context);
        setContentView(R.layout.search_list_dialog);
     //   this.setTitle("SELECT TAGGING");
        mContext = context;
      /*  filterText = (EditText) findViewById(R.id.etxtSearch);
        filterText.addTextChangedListener(filterTextWatcher);*/
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText("SELECT TAGGING");
        list = (RecyclerView) findViewById(R.id.productList);
        //List<String> listData = Arrays.asList(productList);
        // ArrayList<String> listData = new ArrayList<>(Arrays.asList(productList));
        adapter = new TagDialogListAdapter(tagModels, context, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(linearLayoutManager);
        list.setAdapter(adapter);
       /* list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Log.d(TAG, "Selected Item is = "+list.getItemAtPosition(position));
                viewModel.productName.setValue(list.getItemAtPosition(position).toString());
                dismiss();
            }
        });*/
    }

    private TextWatcher filterTextWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {

        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            adapter.getFilter().filter(s);


        }
    };

    @Override
    public void onStop() {
  //      filterText.removeTextChangedListener(filterTextWatcher);
    }

    @Override
    public void cardClicked(TagModels f) {
        Log.e(TAG, "Selected Item is = " + f.TAGID);

        Intent intent = new Intent(mContext, TaggingListActivity.class);
        intent.putExtra(TAG_MODEL_ID, f.TAGID);
        mContext.startActivity(intent);

        dismiss();
    }
}
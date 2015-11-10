package com.dl.domelist.com.dl.address_book;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.dl.domelist.R;

import java.util.ArrayList;

import static com.dl.domelist.R.id.ab_lv_addreses;

public class AddressBookActivity extends AppCompatActivity {

    private Button ab_ref_btn;
    private ListView ab_addreses_lv;
    private ArrayList<String> addressList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);

        initView();
    }

    private void initView() {
        ab_ref_btn = (Button) findViewById(R.id.ab_btn_ref);
        ab_addreses_lv = (ListView) findViewById(ab_lv_addreses);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,addressList);
        ab_addreses_lv.setAdapter(adapter);
        refData();
    }
    private static final String[] PHONES_PROJECTION = new String[] {
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER
            , ContactsContract.CommonDataKinds.Phone.PHOTO_ID,ContactsContract.CommonDataKinds.Phone.CONTACT_ID };
    private void refData() {
        addressList.clear();

        Cursor query = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
        while (query.moveToNext()){
            String name = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            addressList.add(name+"\n"+number);

        }

        if(query!=null){
            query.close();
        }
//        adapter.notifyDataSetChanged();

    }

}

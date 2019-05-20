package fm.dongs.diary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

public class UpdateActivity extends AppCompatActivity {

    private EditText mTitleEdit;
    private EditText mContentEdit;
    private Button mdel_Button;
    private Button mupdate_Button;
    private String mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_item);


        mTitleEdit = (EditText) findViewById(R.id.titleEdit);
        mContentEdit = (EditText) findViewById(R.id.contentEdit);
        mupdate_Button = (Button) findViewById(R.id.update_button);
        mdel_Button = (Button) findViewById(R.id.del_button);

        View.OnClickListener firstOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Article Addition
                String titleText = mTitleEdit.getText().toString();
                String contentText = mContentEdit.getText().toString();
                mTitleEdit.setText("");
                mContentEdit.setText("");

                Realm.init(getApplicationContext());
                Realm realm = Realm.getDefaultInstance();

//           transection
                // TODO: 2019-05-20  어떻게 해야 수정을 할수 있는가
                realm.beginTransaction();
               // Article article = realm.createObject(Article.class);
                ListActivity list = realm.where("titleEdit").equalTo("")
                article.setTitle(titleText);
                article.setContent(contentText);
                realm.commitTransaction();

                finish();
            }
        };


        View.OnClickListener second_OnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realm.init(getApplicationContext());
                Realm realm = Realm.getDefaultInstance();

                //           transection

                realm.beginTransaction();
                final Article article = realm.where(Article.class).equalTo("title",mTitle).findFirst();
                article.deleteFromRealm();
                realm.commitTransaction();

                finish();




            }
        };



        mupdate_Button.setOnClickListener(firstOnClickListener);
        mdel_Button.setOnClickListener(second_OnClickListener);


//TODO: Fix
        if (savedInstanceState == null){
//            String text = getIntent().getStringExtra("item");
//            mTextView.setText(text);
            String title = getIntent().getStringExtra("title");
            String content = getIntent().getStringExtra("content");
            mTitle = title;
            mTitleEdit.setText(title);
            mContentEdit.setText(content);
        }

    }
}

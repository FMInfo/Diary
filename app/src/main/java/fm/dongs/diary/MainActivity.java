package fm.dongs.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private EditText mTitleEdit;
    private EditText mContentEdit;
    private Button mcancel_Button;
    private Button msave_Button;
    private String mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTitleEdit = (EditText) findViewById(R.id.titleEdit);
        mContentEdit = (EditText) findViewById(R.id.contentEdit);
        msave_Button = (Button) findViewById(R.id.save_button);
        mcancel_Button = (Button) findViewById(R.id.cancel_button);

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
                realm.beginTransaction();
                Article article = realm.createObject(Article.class);
                article.setTitle(titleText);
                article.setContent(contentText);
                realm.commitTransaction();

                finish();
            }
        };


        View.OnClickListener second_OnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(new Intent(MainActivity.this,
                        ListActivity.class));
                startActivity(intent);



            }
        };



        msave_Button.setOnClickListener(firstOnClickListener);
        mcancel_Button.setOnClickListener(second_OnClickListener);


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

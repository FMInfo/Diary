package fm.dongs.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ListActivity extends AppCompatActivity {

    private ListView mListView;
    private Button creat_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        creat_button = (Button) findViewById(R.id.creat_button);

        //final String[] sample ={"hello","my","name","is seo"};
//        Article[] articles ={
////                new Article("title 1","content 1 "),
////                new Article("title 2","content 2 "),
////                new Article("title 3","content 3 ")
//        };


        mListView= (ListView) findViewById(R.id.listView);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,
        //        sample);
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance().getDefaultInstance();
        final RealmResults<Article> articles = realm.where(Article.class).findAll();
        final CustomAdapter adapter = new CustomAdapter(this,
                R.layout.list_row,
               // new ArrayList<Article>(Arrays.asList(articles))
                articles
        );

        articles.addChangeListener(new RealmChangeListener<RealmResults<Article>>() {
            @Override
            public void onChange(RealmResults<Article> articles) {
                adapter.notifyDataSetChanged();
            }
        });


        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(new Intent(ListActivity.this,
                        UpdateActivity.class));

         //       intent.putExtra("item", sample[position]);
                intent.putExtra("title",articles.get(position).getTitle());
                intent.putExtra("content",articles.get(position).getContent());
                startActivity(intent);
            }
        });
        View.OnClickListener creatListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListActivity.this,MainActivity.class);
                startActivity(intent);
            }
        };
        creat_button.setOnClickListener(creatListener);
    }
}

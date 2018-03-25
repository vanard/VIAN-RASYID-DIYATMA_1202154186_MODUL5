package com.studycase.vanard.vianrasyiddiyatma_1202154186_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //deklarasi variabel yang akan digunakan
    private TodoHelper dtBase;
    private RecyclerView rcView;
    private TodoListAdapter adapter;
    private ArrayList<TodoItem> data_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent ke class add to do
                Intent intent = new Intent(MainActivity.this, AddTodoActivity.class);
                //memulai intent
                startActivity(intent);
            }
        });
        //Merefer semua variabel yang ada
        //mengakses recyclerview
        rcView = findViewById(R.id.rec_view);
        //membuat araylist baru
        data_list = new ArrayList<>();
        //membuat database baru
        dtBase = new TodoHelper(this);
        //memanggil method readdata
        dtBase.readdata(data_list);

        //menginisialisasi shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //membuat adapter baru
        adapter = new TodoListAdapter(this,data_list, color);
        //mengatur perubahan ukuran
        rcView.setHasFixedSize(true);
        rcView.setLayoutManager(new LinearLayoutManager(this));
        //inisiasi adapter untuk recycler view
        rcView.setAdapter(adapter);

        //menjalankan method hapus data pada list to do
        hapusgeser();
    }

    //membuat method untuk menghapus item pada to do list
    public void hapusgeser(){
        //membuat touch helper baru untuk recycler view
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                TodoItem current = adapter.getData(position);
                //apabila item di swipe ke arah kiri
                if(direction==ItemTouchHelper.LEFT){
                    //remove item yang dipilih dengan mengenali todonya sebagai primary key
                    if(dtBase.removedata(current.getTodo())){
                        //menghapus data
                        adapter.deleteData(position);
                        //membuat snack bar dan pemberitahuan bahwa item sudah terhapus dengan durasi 2 sekon
                        Snackbar.make(findViewById(R.id.coordinator), "List Telah Terhapus", 2000).show();
                    }
                }
            }
        };
        //menentukan itemtouchhelper untuk recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(rcView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

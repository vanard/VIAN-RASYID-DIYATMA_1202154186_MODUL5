package com.studycase.vanard.vianrasyiddiyatma_1202154186_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddTodoActivity extends AppCompatActivity {
    //deklarasi variable yang akan digunakan
    private EditText ToDo, Description, Priority;
    private TodoHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        //merefer semua variabel yang ada
        ToDo = (EditText) findViewById(R.id.edt_Todo);
        Description = (EditText) findViewById(R.id.edt_Desc);
        Priority = (EditText) findViewById(R.id.edt_Priority);
        db = new TodoHelper(this);
    }

    @Override
    public void onBackPressed() {
        //intent menuju MainActivity
        Intent intent = new Intent(AddTodoActivity.this, MainActivity.class);
        //memulai intent
        startActivity(intent);
        //menutup aktivitas setelah intent dijalankan
        this.finish();
    }

    public void addTodo(View view) {
        //apabila data todoname, deskripsi dan prioritas di isi,
        if (db.inputdata(new TodoItem(ToDo.getText().toString(), Description.getText().toString(), Priority.getText().toString()))){
            //maka akan menampilkan toast bawha data berhasil di tambahkan ke dalam list
            Toast.makeText(this, "To Do List Ditambahkan !", Toast.LENGTH_SHORT).show();
            //intent ke mainActivity
            startActivity(new Intent(AddTodoActivity.this, MainActivity.class));
            //menutup aktivitas agar tidak kembali ke activity yang dijalankan setelah intent
            this.finish();
        }else {
            //apabila edit text kosong maka akan muncul toast bahwa tidak bisa menambah ke dalam list
            Toast.makeText(this, "List tidak boleh kosong", Toast.LENGTH_SHORT).show();
            //set semua edit text menjadi kosong
            ToDo.setText(null);
            Description.setText(null);
            Priority.setText(null);
        }
    }
}

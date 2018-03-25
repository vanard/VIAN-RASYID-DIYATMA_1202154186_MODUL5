package com.studycase.vanard.vianrasyiddiyatma_1202154186_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by viani on 25/03/2018.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.holder> {

    //deklarasi variabel yang akan digunakan
    private Context mcontex;
    private List<TodoItem> list;
    int color;

    //konstruktor
    public TodoListAdapter(Context context, List<TodoItem> list, int color){
        this.mcontex=context;
        this.list=list;
        this.color=color;
    }

    //menentukan viewholder untuk recyclerview
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //membuat view baru
        View view = LayoutInflater.from(mcontex).inflate(R.layout.list_todo, parent, false);
        holder hld = new holder(view);
        return hld;
    }

    //menyetting nilai yang didapatkan pada viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        TodoItem data = list.get(position);
        holder.ToDo.setText(data.getTodo());
        holder.Desc.setText(data.getDesc());
        holder.Priority.setText(data.getPrior());
        holder.cv.setCardBackgroundColor(mcontex.getResources().getColor(this.color));
    }

    //mendapatkan jumlah list
    @Override
    public int getItemCount() {
        return list.size();
    }

    //mendapatkan list dari adapter
    public TodoItem getData(int position){
        return list.get(position);
    }

    //untuk menghapus list pada todolist
    public void deleteData(int i){
        //hapus item yang terpilih
        list.remove(i);
        //memberi notifikasi item yang dihapus
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, list.size());
    }

    class holder extends RecyclerView.ViewHolder{
        //deklarasi variable yang akan digunakan
        public TextView ToDo, Desc, Priority;
        public CardView cv;
        public holder(View itemView){
            super(itemView);

            //get id text view dan cardview pada layout
            ToDo = itemView.findViewById(R.id.todo);
            Desc = itemView.findViewById(R.id.description);
            Priority = itemView.findViewById(R.id.number);
            cv = itemView.findViewById(R.id.cardview);
        }
    }
}

package com.studycase.vanard.vianrasyiddiyatma_1202154186_modul5;

/**
 * Created by viani on 25/03/2018.
 */

public class TodoItem {
    //declare variable
    String todo, desc, prior;
    //konstruktor
    public TodoItem(String todo, String desc, String prior){
        this.todo = todo;
        this.desc = desc;
        this.prior = prior;
    }
    //setter getter
    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }
}

package com.example.roomdatasample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.roomdatasample.db.UserEntity

class MainActivity : AppCompatActivity(),RecyclerAdapter.RowClickListener {
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var viewModel: MainActivityViewModel
    lateinit var savebtn: Button
    lateinit var edtname:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savebtn=findViewById(R.id.save_btn)
        edtname=findViewById(R.id.edt_name)

        recyclerView=findViewById(R.id.recyclerview)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerAdapter = RecyclerAdapter(this@MainActivity)
            adapter = recyclerAdapter
            val divider = DividerItemDecoration(applicationContext,VERTICAL)
            addItemDecoration(divider)

        }

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getAllUsersObserver().observe(this, Observer {
            recyclerAdapter.setListData(ArrayList(it))
            recyclerAdapter.notifyDataSetChanged()
        })

        savebtn.setOnClickListener {
            val name = edtname.text.toString()
            if(savebtn.text.equals("SAVE")) {
                val user = UserEntity(0, name)
                viewModel.insertUserInfo(user)
            } else{
                val user = UserEntity(edtname.getTag(edtname.id).toString().toInt(),name)
                viewModel.updateUserInfo(user)
                savebtn.setText("SAVE")
            }
            edtname.setText("")
        }



    }

    override fun onDeleteUserClickListener(user: UserEntity) {

        viewModel.deleteUserInfo(user)
    }

    override fun onItemClickListener(user: UserEntity) {
        edtname.setText(user.namez)
        edtname.setTag(edtname.id,user.id)
        savebtn.setText("Update")
    }
}
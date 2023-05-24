package com.example.umc5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.provider.ContactsContract.PinnedPositions
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc5.database.MemoData
import com.example.umc5.database.MemoDatabase
import com.example.umc5.database.MemoDao
import com.example.umc5.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    private lateinit var getResultText2: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val itemList = ArrayList<DataObj>()
        var modpos=0
        val db = MemoDatabase.getInstance(applicationContext)



        val memoAdapter = MemoListAdapater(itemList)
        memoAdapter.setMemoDatabase(db!!)/*
        thread(start = true) {
            itemList.addAll(db!!.memoDao().restart())
            runOnUiThread {
                memoAdapter.notifyDataSetChanged()
            }
        }*/
        binding.memoList.adapter = memoAdapter
        binding.memoList.layoutManager =LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){result->
            if(result.resultCode==RESULT_OK){
                val mString = result.data?.getStringExtra("text")
                itemList.add(DataObj(mString.toString()))


                memoAdapter.notifyDataSetChanged()
            }
        }

        getResultText2 = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){result->
            if(result.resultCode==RESULT_OK){
                val mString = result.data?.getStringExtra("text")
                itemList[modpos].txt=mString.toString()
                memoAdapter.notifyDataSetChanged()
            }
        }
        val intent = Intent(this,AddMemoActivity::class.java)
        memoAdapter.setOnItemClickListener(object:MemoListAdapater.OnItemClickListener{
            override fun onItemClick(view: View, position:Int){
                Log.d("chanho","클릭됨")
                intent.putExtra("existed",itemList[position].txt.toString())
                getResultText2.launch(intent)
                modpos=position
            }
        })

        binding.addMemoBtn.setOnClickListener {
            val intent = Intent(this,AddMemoActivity::class.java)
            getResultText.launch(intent)
        }

    }
}


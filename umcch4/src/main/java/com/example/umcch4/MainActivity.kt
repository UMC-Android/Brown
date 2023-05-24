package com.example.umcch4

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.umcch4.databinding.ActivityMainBinding

private var textPause:String =""

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        Log.i("life","onCreate")
        setContentView(viewBinding.root)
        val edt = viewBinding.editTextTextMultiLine
        viewBinding.button.setOnClickListener{
            val intent = Intent(this,CheckAcitivity::class.java)
            intent.putExtra("message",edt.text.toString())
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("life","onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("life","onPause")
        val edt = viewBinding.editTextTextMultiLine
        textPause = edt.text.toString()
    }

    override fun onResume() {
        super.onResume()
        Log.i("life","onResume")
        val edt = viewBinding.editTextTextMultiLine
        edt.setText(textPause)
    }

    override fun onStop() {
        super.onStop()
        Log.i("life","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("life","onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        val builder = AlertDialog.Builder(this)
        Log.i("life","onRestart")
        builder.setTitle("이어하기")
            .setMessage("이어서 다시작성하시겠습니까?")
            .setPositiveButton("확인",
            DialogInterface.OnClickListener{dialog,which->
                Toast.makeText(this,"다시 작성합니다",Toast.LENGTH_SHORT).show()
            })
            .setNegativeButton("안함",
            DialogInterface.OnClickListener{dialog,which->
                viewBinding.editTextTextMultiLine.setText("")
            })
        val dialog = builder.create()
        dialog.show()
    }
}
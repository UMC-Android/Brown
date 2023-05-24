package com.example.umc5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.umc5.database.MemoData
import com.example.umc5.database.MemoDatabase
import kotlin.concurrent.thread

class MemoListAdapater(private val itemList:ArrayList<DataObj>):RecyclerView.Adapter<MemoListAdapater.MemoViewHolder>() {
    interface OnItemClickListener{
        fun onItemClick(view:View,position: Int)
    }
    private lateinit var mOnItemClickListener: OnItemClickListener
    private lateinit var memoDatabase: MemoDatabase
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        mOnItemClickListener = onItemClickListener
    }
    fun setMemoDatabase(memoDatabase: MemoDatabase){
        this.memoDatabase=memoDatabase
    }

    inner class MemoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val memoText: TextView = itemView.findViewById<TextView>(R.id.textView)
        init {
            itemView.setOnLongClickListener{
                removeData(adapterPosition)
                notifyItemRemoved(adapterPosition)
                thread(start=true){
                    memoDatabase.memoDao().delItem(adapterPosition)
                }
                false

            }
            itemView.setOnClickListener {
                val pos = adapterPosition
                if(pos!=RecyclerView.NO_POSITION&&mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(itemView,pos)
                }
            }
            thread(start=true) {
                memoDatabase.memoDao().insert(MemoData(memoText.text.toString(), adapterPosition))
            }
        }

    }


    fun removeData(position: Int){
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.memo_list_layout,parent,false)
        return MemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.memoText.text = itemList[position].txt
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
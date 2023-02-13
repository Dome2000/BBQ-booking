package com.example.haruto

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.haruto.databinding.EditDeleteItemLayout2Binding


class ReserveAdater2(val items : List<Reserve>,val context: Context) : RecyclerView.Adapter<ReserveAdater2.ViewHolder>() {
    inner class ViewHolder (view: View, val binding:EditDeleteItemLayout2Binding):RecyclerView.ViewHolder(view){
        init {
            binding.tvEditDelete2.setOnClickListener{
                val item = items[adapterPosition]
                val context_v: Context = view.context
                val intent = Intent(context_v, EditDeleteEMPActivity::class.java)
                intent.putExtra("mId", item.reserve_id)
                intent.putExtra("mUsername", item.customer_username)
                intent.putExtra("mDate", item.reserve_date)
                intent.putExtra("mTime", item.reserve_time)
                intent.putExtra("mCount", item.count.toString())
                intent.putExtra("mStatus",item.status_id.toString())
                context_v.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReserveAdater2.ViewHolder {
            val binding = EditDeleteItemLayout2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
            return ViewHolder(binding.root,binding)
    }

    override fun onBindViewHolder(holder: ReserveAdater2.ViewHolder, position: Int) {
        var status:Int = items[position].status_id
        val binding_holder = holder.binding
        if (status == 1){
            binding_holder.tvID2.text = "รหัสการจอง  " + items[position].reserve_id
            binding_holder.tvUser2.text = "ผู้ทำการจอง  "+ items[position].customer_username
            binding_holder.tvDate2.text = "วันที่จอง     "+items[position].reserve_date
            binding_holder.tvTime2.text = "เวลาที่จอง    "+items[position].reserve_time
            binding_holder.tvCount2.text = "จำนวนคน     "+items[position].count
            binding_holder.tvStatus2.text = "สถานะ     รอยืนยัน"
        }
        else if (status == 2){
            binding_holder.tvID2.text = "รหัสการจอง  " + items[position].reserve_id
            binding_holder.tvUser2.text = "ผู้ทำการจอง  "+ items[position].customer_username
            binding_holder.tvDate2.text = "วันที่จอง     "+items[position].reserve_date
            binding_holder.tvTime2.text = "เวลาที่จอง    "+items[position].reserve_time
            binding_holder.tvCount2.text = "จำนวนคน     "+items[position].count
            binding_holder.tvStatus2.text = "สถานะ     รอคิว"
        }
        else if (status == 3){
            binding_holder.tvID2.text = "รหัสการจอง  " + items[position].reserve_id
            binding_holder.tvUser2.text = "ผู้ทำการจอง  "+ items[position].customer_username
            binding_holder.tvDate2.text = "วันที่จอง     "+items[position].reserve_date
            binding_holder.tvTime2.text = "เวลาที่จอง    "+items[position].reserve_time
            binding_holder.tvCount2.text = "จำนวนคน     "+items[position].count
            binding_holder.tvStatus2.text = "สถานะ     สำเร็จ"
        }
        else if (status == 4){
            binding_holder.tvID2.text = "รหัสการจอง  " + items[position].reserve_id
            binding_holder.tvUser2.text = "ผู้ทำการจอง  "+ items[position].customer_username
            binding_holder.tvDate2.text = "วันที่จอง     "+items[position].reserve_date
            binding_holder.tvTime2.text = "เวลาที่จอง    "+items[position].reserve_time
            binding_holder.tvCount2.text = "จำนวนคน     "+items[position].count
            binding_holder.tvStatus2.text = "สถานะ     ยกเลิก"
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
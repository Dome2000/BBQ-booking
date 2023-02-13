package com.example.haruto

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.haruto.databinding.EditDeleteItemLayoutBinding

class ReserveAdater(val items : List<Reserve>,val context: Context) : RecyclerView.Adapter<ReserveAdater.ViewHolder>() {
    inner class ViewHolder (view: View, val binding:EditDeleteItemLayoutBinding):RecyclerView.ViewHolder(view){
        init {
            binding.tvEditDelete.setOnClickListener{
                val item = items[adapterPosition]
                val context_v: Context = view.context
                val intent = Intent(context_v, EditDeleteActivity::class.java)
                intent.putExtra("mId", item.reserve_id)
                intent.putExtra("mDate", item.reserve_date)
                intent.putExtra("mTime", item.reserve_time)
                intent.putExtra("mCount", item.count.toString())
                intent.putExtra("mStatus",item.status_id.toString())
                context_v.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReserveAdater.ViewHolder {
        val binding = EditDeleteItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ReserveAdater.ViewHolder, position: Int) {
        var status:Int = items[position].status_id
        val binding_holder = holder.binding
        if (status == 1){
            binding_holder.tvID.text = "รหัสการจอง  " + items[position].reserve_id
            binding_holder.tvDate.text = "วันที่จอง     "+items[position].reserve_date
            binding_holder.tvTime.text = "เวลาที่จอง    "+items[position].reserve_time
            binding_holder.tvCount.text = "จำนวนคน     "+items[position].count
            binding_holder.tvStatus.text = "สถานะ     รอยืนยัน"
        }
        else if (status == 2){
            binding_holder.tvID.text = "รหัสการจอง  " + items[position].reserve_id
            binding_holder.tvDate.text = "วันที่จอง     "+items[position].reserve_date
            binding_holder.tvTime.text = "เวลาที่จอง    "+items[position].reserve_time
            binding_holder.tvCount.text = "จำนวนคน     "+items[position].count
            binding_holder.tvStatus.text = "สถานะ      รอคิว"
        }
        else if (status == 3){
            binding_holder.tvID.text = "รหัสการจอง  " + items[position].reserve_id
            binding_holder.tvDate.text = "วันที่จอง     "+items[position].reserve_date
            binding_holder.tvTime.text = "เวลาที่จอง    "+items[position].reserve_time
            binding_holder.tvCount.text = "จำนวนคน     "+items[position].count
            binding_holder.tvStatus.text = "สถานะ      สำเร็จ"
        }

    }
    override fun getItemCount(): Int {
        return items.size
    }

}
package com.info.retrofitplaceholder.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.info.retrofitplaceholder.R
import com.info.retrofitplaceholder.pojo.Result

class PopularAdapter(private val values: List<Result>, context: Context) :
        RecyclerView.Adapter<PopularAdapter.MyViewHolder>() {

    private var context = context
    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvFilmName?.text = values[position].title
        holder.tvReleaseDate?.text = values[position].release_date
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500${values[position].backdrop_path}")
                .into(object : CustomTarget<Drawable?>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    override fun onResourceReady(resource: Drawable, @Nullable transition: Transition<in Drawable?>?) {
                        holder.clBackground?.background = resource
                    }

                    override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
                })
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvFilmName: TextView? = null
        var tvReleaseDate: TextView? = null
        var clBackground: ConstraintLayout? = null

        init {
            tvFilmName = itemView.findViewById(R.id.tvFilmName)
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate)
            clBackground = itemView.findViewById(R.id.background)
        }
    }
}
package com.example.fdapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fdapp.R
import com.example.fdapp.model.Category
import com.squareup.picasso.Picasso

interface OnClickListener {
    fun onClick(category: Category)
}
class CategoryAdapter(private val onClickListener: OnClickListener) : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(
    CategoryDiffCalldack()
),View.OnClickListener {

    private var categoryList = emptyList<Category>()
    //var onCategoryItemClickListener: ((Category) -> Unit)? = null
    //private var onClickListener: OnClickListener? = null

    inner class CategoryViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        /*fun bind(category: Category) {
            itemBinding.tvCategoryTitle.text = category.name
        }*/

        /*val name = itemBinding.tvCategoryTitle
        val img = itemBinding.ivCategoryImage*/
            val name = view.findViewById<TextView>(R.id.tvCategoryTitle)
            val img = view.findViewById<ImageView>(R.id.ivCategoryImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item,parent,false)
        val viewHolder = CategoryViewHolder(view)
        view.setOnClickListener(this)
        return viewHolder
        /*val view = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        val viewHolder = CategoryViewHolder(view)


        viewHolder.img.setOnClickListener {
            onCategoryItemClickListener?.invoke(getItem(viewHolder.adapterPosition))
            true
        }
        return viewHolder*/
    }

    override fun getItemCount(): Int = categoryList.size

    /*override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return VIEW_TYPE_ENABLED
    }*/

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.name.text = category.name

        Picasso.with(holder.img.context)
            .load(categoryList[position].image_url)
            .into(holder.img)

        holder.itemView.tag = category
    }

    fun setList(category: List<Category>) {
        categoryList = category
        notifyDataSetChanged()
    }

    override fun onClick(view: View?) {
        val category = view?.tag as Category
        onClickListener.onClick(category)
    }

}
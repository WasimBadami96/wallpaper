package base.image.mywallpaper.Presentation.Fragment.ViewModel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import base.image.mywallpaper.R
import com.google.common.io.BaseEncoding.base64

class CategoriesAdapter(val list: MutableList<catModel?>) : RecyclerView.Adapter<CategoriesAdapter.viewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categoryitemlayout,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        val item = list[position]
        holder.txt.text = item?.name
/*if (item != null) {
            holder.img.setImageResource(item.Image)
        }*/

        val mBitmap = convertBase64ToBitmap(item!!.Image)
        if (mBitmap != null)
            holder.img.setImageBitmap(mBitmap)
        else
        {

        }
    }

    override fun getItemCount(): Int {

        return list.size
    }

    inner class viewHolder(itemview : View) : RecyclerView.ViewHolder(itemview)
    {
        var img = itemview.findViewById<ImageView>(R.id.image_view)
        var txt = itemview.findViewById<TextView>(R.id.name)
    }
    private fun convertBase64ToBitmap(b64: String): Bitmap? {

        val solution = b64.split("base64,")[1];
        val imageAsBytes = Base64.decode(solution.toByteArray(), Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size)
    }

}

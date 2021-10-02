package base.image.mywallpaper.Presentation.Fragment

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import base.image.mywallpaper.Presentation.Fragment.ViewModel.CategoriesAdapter
import base.image.mywallpaper.Presentation.Fragment.ViewModel.catModel
import base.image.mywallpaper.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class CategoriesFragment : Fragment() {
    lateinit var mRecyclerView: RecyclerView
    val database = FirebaseDatabase.getInstance()
    private lateinit var progressBar : ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catagories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerView = view.findViewById(R.id.recycler_viewlayout)
        progressBar = view.findViewById(R.id.progress)
        //firebase data

        val mRef = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://wallpaperapp-bcb60-default-rtdb.firebaseio.com")
            .child("Wallpaper/")

        mRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val rList: MutableList<catModel?> = ArrayList<catModel?>()
                for (postSnapshot in dataSnapshot.children) {
                    try {
                        val rec: catModel? = postSnapshot.getValue(catModel::class.java)
                        rList.add(rec)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
                if (rList  !=null)
                {
                    progressBar.visibility = View.VISIBLE
                }
                progressBar.visibility = View.INVISIBLE
                mRecyclerView.adapter = CategoriesAdapter(rList)

            }

            override fun onCancelled(databaseError: DatabaseError) {

                Toast.makeText(
                    activity,
                    databaseError.message + "  " + databaseError.details, Toast.LENGTH_LONG
                ).show()
            }
        })


    }
}
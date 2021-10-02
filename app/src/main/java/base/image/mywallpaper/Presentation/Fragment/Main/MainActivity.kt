package base.image.mywallpaper.Presentation.Fragment.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import base.image.mywallpaper.Presentation.Fragment.*
import base.image.mywallpaper.Presentation.Fragment.ViewModel.MyPagerAdapter
import base.image.mywallpaper.R
import base.image.mywallpaper.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager2

        val mFragList = arrayListOf<Fragment>(
            CategoriesFragment(),
            RecentFragment() ,
            RandomFragment () ,
            PopularFragment (),
            MostPopularFragment() ,
            WeeklyPopularFragment()
        )
        val adapter = MyPagerAdapter(mFragList,supportFragmentManager ,lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager )
        {
                tab ,position ->

            when (position) {
                0 -> {
                    tab.text = "CATEGORIES"
                }
                1 -> {
                    tab.text = "RECENT"
                }
                2 -> {
                    tab.text = "RANDOM"
                }
                3 -> {
                    tab.text = "WEEKLY POPULAR"
                }
                4 -> {
                    tab.text = "MOST POPULAR"
                }
            }
        }.attach()
    }

}
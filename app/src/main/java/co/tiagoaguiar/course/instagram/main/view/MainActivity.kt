package co.tiagoaguiar.course.instagram.main.view

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.camera.view.CameraFragment
import co.tiagoaguiar.course.instagram.common.extention.replaceFragment
import co.tiagoaguiar.course.instagram.databinding.ActivityMainBinding
import co.tiagoaguiar.course.instagram.home.view.HomeFragment
import co.tiagoaguiar.course.instagram.profile.view.ProfileFragment
import co.tiagoaguiar.course.instagram.search.view.SearchFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragment: Fragment

    private lateinit var searchFragment: Fragment

    private lateinit var cameraFragment: Fragment

    private lateinit var profileFragment: Fragment

    private lateinit var currenteFragment: Fragment

    private lateinit var fragmentSavedState: HashMap<String, Fragment.SavedState?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
            window.statusBarColor = ContextCompat.getColor(this, R.color.gray)
        }

        setSupportActionBar(binding.mainToolBar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_insta_camera)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        if (savedInstanceState == null) {
            fragmentSavedState = HashMap()
        } else {
            savedInstanceState.getSerializable("fragmentState") as HashMap<String, Fragment.SavedState?>
        }

        homeFragment = HomeFragment()
        searchFragment = SearchFragment()
        cameraFragment = CameraFragment()
        profileFragment = ProfileFragment()

//        currenteFragment = homeFragment

//        supportFragmentManager.beginTransaction().apply {
//            add(R.id.main_fragment, profileFragment, "3").hide(profileFragment)
//            add(R.id.main_fragment, cameraFragment, "2").hide(cameraFragment)
//            add(R.id.main_fragment, searchFragment, "1").hide(searchFragment)
//            add(R.id.main_fragment, homeFragment, "0")
//            commit()
//        }

        binding.mainBottomNav.setOnNavigationItemSelectedListener(this)
//        binding.mainBottomNav.selectedItemId = R.id.menu_bottom_home
    }

    private fun setScrollToolbarEnabled(enabled: Boolean) {
        val params = binding.mainToolBar.layoutParams as AppBarLayout.LayoutParams
        var coordinatorParams = binding.mainAppBar.layoutParams as CoordinatorLayout.LayoutParams

        if (enabled) {
            params.scrollFlags =
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
            coordinatorParams.behavior = AppBarLayout.Behavior()
            binding.mainAppBar.layoutParams = coordinatorParams

        } else {
            params.scrollFlags = 0
            coordinatorParams.behavior = null
            binding.mainAppBar.layoutParams = coordinatorParams
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("fragmentState", fragmentSavedState)
        super.onSaveInstanceState(outState)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var scrollToolbarEnabled = false

        val newFrag: Fragment? = when(item.itemId) {
            R.id.menu_bottom_home -> {
                HomeFragment()
            }
            R.id.menu_bottom_profile -> {
                ProfileFragment()
            } else -> null
        }

        val currFragment = supportFragmentManager.findFragmentById(R.id.main_fragment)

        val fragmentTag = newFrag?.javaClass?.simpleName

        if (!currFragment?.tag.equals(fragmentTag)) {
            currFragment?.let { frag ->
                fragmentSavedState.put(
                    frag.tag!!,
                    supportFragmentManager.saveFragmentInstanceState(frag)
                )
            }
        }

        newFrag?.setInitialSavedState(fragmentSavedState[fragmentTag])
        newFrag?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, it, fragmentTag)
                .addToBackStack(fragmentTag)
                .commit()
        }

//        V1
//        when (item.itemId) {
//            R.id.menu_bottom_home -> {
//                if (currenteFragment == homeFragment) return false
//                supportFragmentManager.beginTransaction().hide(currenteFragment).show(homeFragment).commit()
//                currenteFragment = homeFragment
//
//            }
//            R.id.menu_bottom_search -> {
//                if (currenteFragment == searchFragment) return false
//                supportFragmentManager.beginTransaction().hide(currenteFragment).show(searchFragment).commit()
//                currenteFragment = searchFragment
//
//            }
//            R.id.menu_bottom_add -> {
//                if (currenteFragment == cameraFragment) return false
//                supportFragmentManager.beginTransaction().hide(cameraFragment).show(homeFragment).commit()
//                currenteFragment = cameraFragment
//
//            }
//            R.id.menu_bottom_profile -> {
//                if (currenteFragment == profileFragment) return false
//                supportFragmentManager.beginTransaction().hide(currenteFragment).show(profileFragment).commit()
//                currenteFragment = profileFragment
//                scrollToolbarEnabled = true
//            }
//        }

        setScrollToolbarEnabled(scrollToolbarEnabled)

//        currenteFragment?.let {
//
//            replaceFragment(R.id.main_fragment, it)
//
//        }

        return true
    }
}
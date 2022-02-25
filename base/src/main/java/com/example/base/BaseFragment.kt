package com.example.base

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.shasin.notificationbanner.Banner

/**
 * Base class for all [Fragment] instances
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    abstract val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> VB

    protected val binding : VB
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindLayout.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView(savedInstanceState)
    }

    abstract fun prepareView(savedInstanceState: Bundle?)

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun showSuccessBanner(message: String?) {
        showBanner(message!!, Banner.SUCCESS)
    }

    fun showErrorBanner(message: String?) {
        showBanner(message!!, Banner.ERROR)
    }

    private  fun showBanner(message: String, status: Int) {
        showBanner(null, message, status)
    }

    private  fun showBanner(label:String?,message:String?,status:Int){
        val banner = Banner.make(
            _binding?.root,
            _binding?.root?.context,
            Banner.TOP,
            R.layout.banner_layout
        )
        (banner.bannerView.findViewById<View>(R.id.banner_message) as TextView).text = message
        val statusIcon =
            banner.bannerView.findViewById<ImageView>(R.id.status_icon)
        val labelView = banner.bannerView.findViewById<TextView>(R.id.banner_label)
        if (status == Banner.SUCCESS) {
            statusIcon.background = ResourcesCompat.getDrawable(resources,R.drawable.ic_stat_done,null)
            banner.bannerView
                .setBackgroundColor(getColor(resources,R.color.colorSuccess,null))
            labelView.text =
                if (TextUtils.isEmpty(label)) resources.getString(R.string.banner_success) else label
        } else if (status == Banner.ERROR) {
            statusIcon.background = ResourcesCompat.getDrawable(resources,R.drawable.ic_stat_error,null)
            banner.bannerView.setBackgroundColor(getColor(resources,R.color.colorError,null))
            labelView.text =
                if (TextUtils.isEmpty(label)) resources.getString(R.string.banner_error) else label
        }
        banner.duration = 4000
        banner.bannerView
            .setOnClickListener { v: View? -> banner.dismissBanner() }
        banner.show()
    }
}
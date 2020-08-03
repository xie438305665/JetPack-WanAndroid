package com.wanandroid.developer

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import coil.api.load
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.wanandroid.bridge.adapter.SimpleAdapterListener
import com.wanandroid.bridge.adapter.SimpleMultipleAdapter
import com.wanandroid.bridge.adapter.SimpleMultipleItem
import com.wanandroid.bridge.adapter.SimpleMultipleType
import com.wanandroid.bridge.annotation.ARouterPath
import com.wanandroid.bridge.annotation.AnnotationValue.Companion.SP_KEY_USER_INFO
import com.wanandroid.bridge.annotation.AnnotationValue.Companion.SP_KEY_USER_NAME
import com.wanandroid.bridge.base.BaseActivity
import com.wanandroid.bridge.ext.*
import com.wanandroid.bridge.util.GsonUtils
import com.wanandroid.bridge.util.SpUtils
import com.wanandroid.module.home.ui.HomeHotSearchActivity
import com.wanandroid.module.user.ui.activity.AboutActivity
import com.wanandroid.module.user.ui.activity.CollectActivity
import com.wanandroid.module.user.ui.activity.LoginActivity
import com.wanandroid.module.user.ui.activity.SettingActivity
import com.zhixinhuixue.library.net.entity.UserInfoEntity
import com.zhixinhuixue.library.widget.custom.CustomToolbar
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*

/**
 *  @description:主窗口
 *  @author xcl qq:244672784
 *  @Date 2020/7/6
 **/
@Route(path = ARouterPath.MAIN)
class MainActivity : BaseActivity<MutableList<SimpleMultipleItem>, MainViewModel>(),
    SimpleAdapterListener<SimpleMultipleItem, BaseViewHolder>,
    Observer<MutableList<SimpleMultipleItem>> {
    private lateinit var mAdapter: FragmentStateAdapter
    private lateinit var mDrawerAdapter: SimpleMultipleAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initCreate(bundle: Bundle?) {
        initDrawerMenu()
        initViewPageAdapter()
        bottomNavigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navTabHome -> {
                    mToolbar.visible()
                    mainViewPage.setCurrentItem(0, false)
                }
                R.id.navTabProject -> {
                    mToolbar.gone()
                    mainViewPage.setCurrentItem(1, false)
                }
                R.id.navigation_notifications -> {
                    mToolbar.gone()
                    mainViewPage.setCurrentItem(2, false)
                }
                R.id.navWxArticle -> {
                    mToolbar.gone()
                    mainViewPage.setCurrentItem(3, false)
                }
            }
            true
        }
    }

    override fun initObserver() {
        baseVm.mainVm.observe(this, this)
    }

    override fun initToolbar(toolbar: CustomToolbar) {
        super.initToolbar(toolbar)
        toolbar.setLeftIcon(R.drawable.ic_user)
        toolbar.setTitleText("首页")
        toolbar.setMenuIcon(R.drawable.ic_search)
    }

    /**
     * 侧边栏设置Adapter
     */
    private fun initDrawerMenu() {
        mDrawerMenu.setHasFixedSize(true)
        val layoutParams = mDrawerMenu.layoutParams
        layoutParams.width = (getScreenWidth() / 1.3).toInt()
        mDrawerMenu.layoutParams = layoutParams
        mDrawerAdapter = SimpleMultipleAdapter(
            mutableListOf(),
            this,
            mutableListOf(
                SimpleMultipleType(SimpleMultipleType.HEADER, R.layout.item_drawer_menu_header),
                SimpleMultipleType(SimpleMultipleType.ITEM, R.layout.item_drawer_menu_item),
                SimpleMultipleType(SimpleMultipleType.LINE, R.layout.item_drawer_menu_line)
            )
        )
        mDrawerMenu.adapter = mDrawerAdapter
    }

    /**
     * viewPage 设置Adapter
     */
    private fun initViewPageAdapter() {
        mAdapter = MainAdapter(this)
        mainViewPage.isUserInputEnabled = false
        mainViewPage.adapter = mAdapter
    }

    override fun refreshView(data: MutableList<SimpleMultipleItem>?) {
        data ?: return
        mDrawerAdapter.data.clear()
        mDrawerAdapter.data.addAll(data)
        mDrawerAdapter.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: SimpleMultipleItem, position: Int) {
        when (item.itemType) {
            SimpleMultipleType.HEADER -> {
                mDrawerAdapter.addChildLongClickViewIds(R.id.item_drawer_header_user)
                val tvUser = holder.getView<AppCompatTextView>(R.id.item_drawer_header_user)
                val tvTips = holder.getView<AppCompatTextView>(R.id.item_drawer_header_tips)
                val ivHeader = holder.getView<CircleImageView>(R.id.item_drawer_header_icon)
                if (item.content.toString().isEmpty()) {
                    tvUser.text = "登录/注册"
                    tvTips.visibleOrGone(false)
                    tvUser.setOnClickListener {
                        toStartActivity(LoginActivity::class.java)
                        mDrawerLayout.closeDrawer(GravityCompat.START)
                    }
                    ivHeader.setImageDrawable(R.mipmap.ic_launcher.getDrawable())
                } else {
                    val entity = item.content as UserInfoEntity
                    tvUser.text = SpUtils.getValue(SP_KEY_USER_NAME, "神秘人")
                    tvTips.text = "积分:${entity.coinCount}\t\t\t\t排名:${entity.rank}"
                    tvTips.visibleOrGone(true)
                    ivHeader.load(entity.icon, builder = { placeholder(R.mipmap.ic_launcher) })
                }
            }
            SimpleMultipleType.ITEM -> {
                val ivItem = holder.getView<AppCompatImageView>(R.id.iv_item_drawer_menu)
                when (position) {
                    2 -> {
                        ivItem.setImageDrawable(R.drawable.ic_article_collect.getDrawable())
                    }
                    3 -> {
                        ivItem.setImageDrawable(R.drawable.ic_drawer_menu_article.getDrawable())
                    }
                    4 -> {
                        ivItem.setImageDrawable(R.drawable.ic_drawer_menu_about.getDrawable())
                    }
                    5 -> {
                        ivItem.setImageDrawable(R.drawable.ic_drawer_menu_setting.getDrawable())
                    }
                    else -> {
                        ivItem.setImageDrawable(R.drawable.ic_login.getDrawable())
                    }
                }
                holder.setText(R.id.tv_item_drawer_menu, item.content.toString())
            }
        }
    }

    override fun onBindItemClick(
        adapter: BaseQuickAdapter<SimpleMultipleItem, BaseViewHolder>,
        view: View,
        item: SimpleMultipleItem,
        position: Int
    ) {
        super.onBindItemClick(adapter, view, item, position)
        when (position) {
            2 -> toStartActivity(CollectActivity::class.java)
            3 -> toStartActivity(AboutActivity::class.java)
            4 -> toStartActivity(AboutActivity::class.java)
            5 -> toStartActivity(SettingActivity::class.java)
            else -> baseVm.logout(this)
        }
    }

    override fun onFinishClick() {
        baseVm.initDrawer(
            GsonUtils.toClazz(
                SpUtils.getValue(SP_KEY_USER_INFO, ""),
                UserInfoEntity::class.java
            )
        )
        mDrawerLayout.openDrawer(GravityCompat.START)
    }

    override fun onMenuClick() {
        toStartActivity(HomeHotSearchActivity::class.java)
    }

    override fun onChanged(t: MutableList<SimpleMultipleItem>?) {
        refreshView(t)
    }
}
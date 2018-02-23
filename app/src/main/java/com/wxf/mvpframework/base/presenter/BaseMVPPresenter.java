package com.wxf.mvpframework.base.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wxf.mvpframework.base.view.IBaseMVPView;

/**
 * Created by Administrator on 2018/1/7.
 */
public class BaseMVPPresenter<V extends IBaseMVPView>
{

    /** View层 */
    private V mView;

    /**
     * presenter创建后调用
     *
     * @param savedState
     */
    public void onCreatePresenter(@Nullable Bundle savedState)
    {

    }

    /**
     * 绑定view
     *
     * @param view
     *          实现IBaseMVPView接口的view
     */
    public void onAttachMVPView(V view)
    {
        mView = view;
    }

    /**
     * 解除绑定
     */
    public void onDetachMVPView()
    {
        mView = null;
    }

    /**
     * 被销毁时调用
     */
    public void onDestroyPresenter()
    {

    }

    /**
     * 获取当前View
     *
     * @return 当前View
     */
    public V getMVPView()
    {
        return mView;
    }

    /**
     * 在presenter意外销毁时被调用(如:Activity、Fragment、View中的onSaveInstanceState)
     *
     * @param outState
     *
     */
    public void onSaveInstanceState(Bundle outState)
    {

    }
}

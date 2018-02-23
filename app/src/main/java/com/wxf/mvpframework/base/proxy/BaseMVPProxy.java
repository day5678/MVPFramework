package com.wxf.mvpframework.base.proxy;

import android.os.Bundle;

import com.wxf.mvpframework.base.factory.IPresenterMVPFactory;
import com.wxf.mvpframework.base.presenter.BaseMVPPresenter;
import com.wxf.mvpframework.base.view.IBaseMVPView;

/**
 * Created by Administrator on 2018/1/7.
 */

public class BaseMVPProxy<V extends IBaseMVPView,P extends BaseMVPPresenter<V>> implements IPresenterProxy
{
    private static final String PRESENTER_KEY = "presenter_key";

    private IPresenterMVPFactory<V,P> mFactory;
    private P mPresenter;
    private Bundle mBundle;
    private boolean mIsAttachView;

    public BaseMVPProxy(IPresenterMVPFactory factory)
    {
        mFactory = factory;
    }

    /**
     * 设置Presenter的工厂类,这个方法只能在创建Presenter之前调用,也就是调用getMvpPresenter()之前，如果Presenter已经创建则不能再修改
     *
     * @param presenterFactory
     *          PresenterFactory类型
     */
    @Override
    public void setPresenterFactory(IPresenterMVPFactory presenterFactory) {
        if(null != mFactory)
        {
            throw new IllegalArgumentException("这个方法只能在getMvpPresenter()之前调用，如果Presenter已经创建则不能再修改");
        }
        mFactory = presenterFactory;
    }

    /**
     * 获取Presenter的工厂类
     *
     * @return PresenterMvpFactory类型
     */
    @Override
    public IPresenterMVPFactory getPresenterFactory() {
        return mFactory;
    }

    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter,如果之前创建过，而且是以外销毁则从Bundle中恢复
     */
    @Override
    public P getMVPPresenter() {
        if(null != mFactory)
        {
            if(null == mPresenter)
            {
                mPresenter = mFactory.createMVPPresenter();
                mPresenter.onCreatePresenter(null == mBundle ? null : mBundle.getBundle(PRESENTER_KEY));
            }
        }

        return mPresenter;
    }

    /**
     * 绑定Presenter和view
     *
     * @param mvpView
     *          需要绑定的view
     */
    public void onResume(V mvpView)
    {
        getMVPPresenter();
        if(null != mPresenter && !mIsAttachView)
        {
            mPresenter.onAttachMVPView(mvpView);
            mIsAttachView = true;
        }
    }

    /**
     * 销毁Presenter持有的View
     */
    private void onDetachMVPView()
    {
        if(null != mPresenter && mIsAttachView)
        {
            mPresenter.onDetachMVPView();
            mIsAttachView = false;
        }
    }

    /**
     * 销毁Presenter
     */
    public void onDestroy()
    {
        if(null != mPresenter)
        {
            onDetachMVPView();
            mPresenter.onDestroyPresenter();
            mPresenter = null;
        }
    }

    /**
     * 意外销毁的时候调用
     *
     * @return Bundle(存入回调给Presenter的Bundle和当前Presenter的id)
     */
    public Bundle onSaveInstanceState()
    {
        Bundle bundle = new Bundle();
        getMVPPresenter();
        if(null != mPresenter)
        {
            Bundle presenterBundle = new Bundle();
            //回调Presenter
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENTER_KEY,presenterBundle);
        }
        return bundle;
    }

    /**
     * 意外关闭恢复Presenter
     *
     * @param savedInstanceState
     *          意外关闭时存储的Bundle
     */
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        mBundle = savedInstanceState;
    }
}

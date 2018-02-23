package com.wxf.mvpframework.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wxf.mvpframework.base.factory.IPresenterMVPFactory;
import com.wxf.mvpframework.base.factory.PresenterMVPFactoryImpl;
import com.wxf.mvpframework.base.presenter.BaseMVPPresenter;
import com.wxf.mvpframework.base.proxy.BaseMVPProxy;
import com.wxf.mvpframework.base.proxy.IPresenterProxy;

/**
 * 继承自Activity的基类MvpActivity
 * 使用代理模式来代理Presenter的创建、销毁、绑定、解绑以及Presenter的状态保存,其实就是管理Presenter的生命周期
 */

public abstract class BaseMVPActivity<V extends IBaseMVPView,P extends BaseMVPPresenter<V>> extends AppCompatActivity implements IPresenterProxy<V,P>
{
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";

    private BaseMVPProxy<V,P> mProxy = new BaseMVPProxy<>(PresenterMVPFactoryImpl.<V,P>createFactory(getClass()));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if(null != savedInstanceState)
        {
            mProxy.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mProxy.onResume((V)this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mProxy.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY,outState);
    }

    @Override
    public void setPresenterFactory(IPresenterMVPFactory<V, P> presenterFactory)
    {
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public IPresenterMVPFactory<V, P> getPresenterFactory()
    {
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getMVPPresenter()
    {
        return mProxy.getMVPPresenter();
    }
}

package com.wxf.mvpframework.base.proxy;

import com.wxf.mvpframework.base.factory.IPresenterMVPFactory;
import com.wxf.mvpframework.base.presenter.BaseMVPPresenter;
import com.wxf.mvpframework.base.view.IBaseMVPView;

/**
 * 代理接口
 */
public interface IPresenterProxy<V extends IBaseMVPView,P extends BaseMVPPresenter<V>>
{
    /**
     * 设置创建Presenter的工厂
     *
     * @param presenterFactory
     *          IPresenterMVPFactory类型
     */
    void setPresenterFactory(IPresenterMVPFactory<V,P> presenterFactory);

    /**
     * 获取Presenter的工厂类
     *
     * @return 返回IPresenterMVPFactory类型
     */
    IPresenterMVPFactory<V,P> getPresenterFactory();

    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter
     */
    P getMVPPresenter();
}

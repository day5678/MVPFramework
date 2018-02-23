package com.wxf.mvpframework.base.factory;

import com.wxf.mvpframework.base.presenter.BaseMVPPresenter;
import com.wxf.mvpframework.base.view.IBaseMVPView;

/**
 * Presenter工厂接口
 */
public interface IPresenterMVPFactory<V extends IBaseMVPView,P extends BaseMVPPresenter<V>>
{
    /**
     * 创建Presenter的接口方法
     *
     * @return 需要创建的Presenter
     */
    P createMVPPresenter();
}

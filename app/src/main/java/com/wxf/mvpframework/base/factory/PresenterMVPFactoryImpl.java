package com.wxf.mvpframework.base.factory;

import com.wxf.mvpframework.base.presenter.BaseMVPPresenter;
import com.wxf.mvpframework.base.view.IBaseMVPView;

/**
 * Presenter工厂实现类
 */

public class PresenterMVPFactoryImpl<V extends IBaseMVPView,P extends BaseMVPPresenter<V>> implements IPresenterMVPFactory<V,P>
{
    private final Class<P> mPresenterClass;

    private PresenterMVPFactoryImpl(Class<P> presenterClass)
    {
        mPresenterClass = presenterClass;
    }

    /**
     * 根据注解创建Presenter的工厂实现类
     *
     * @param viewClazz
     *          需要创建Presenter的V层实现类
     * @param <V>
     *          当前View实现的接口类型
     * @param <P>
     *          当前要创建的Presenter类型
     *
     * @return 工厂类
     */
    public static <V extends IBaseMVPView,P extends BaseMVPPresenter<V>> PresenterMVPFactoryImpl<V,P> createFactory(Class<?> viewClazz)
    {
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> pClass = null;
        if(null != annotation)
        {
            pClass = (Class<P>) annotation.value();
        }

        return pClass == null ? null : new PresenterMVPFactoryImpl<V,P>(pClass);
    }

    @Override
    public P createMVPPresenter()
    {
        try
        {
            return mPresenterClass.newInstance();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
        }
    }
}

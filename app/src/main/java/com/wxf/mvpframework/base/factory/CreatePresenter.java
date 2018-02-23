package com.wxf.mvpframework.base.factory;

import com.wxf.mvpframework.base.presenter.BaseMVPPresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 创建Presenter的注解
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter
{
    Class<? extends BaseMVPPresenter> value();
}

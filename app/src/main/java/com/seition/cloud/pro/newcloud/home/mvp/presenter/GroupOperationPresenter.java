package com.seition.cloud.pro.newcloud.home.mvp.presenter;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.SupportActivity;

import com.jess.arms.base.bean.DataBean;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.seition.cloud.pro.newcloud.home.mvp.contract.GroupContract;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import okhttp3.MultipartBody;


@ActivityScope
public class GroupOperationPresenter extends BasePresenter<GroupContract.GroupOperationModel, GroupContract.GroupOperationView> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;


    @Inject
    public GroupOperationPresenter(GroupContract.GroupOperationModel model, GroupContract.GroupOperationView rootView) {
        super(model, rootView);
    }


/*
    */
/**
     * 使用 2017 Google IO 发布的 Architecture Components 中的 Lifecycles 的新特性 (此特性已被加入 Support library)
     * 使 {@code Presenter} 可以与 {@link SupportActivity} 和 {@link Fragment} 的部分生命周期绑定
     *//*

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {

//        requestUsers(true);//打开 App 时自动加载列表
    }
*/


    /*p
    * */
    public  void createGroup(  String  name,  int group_logo, String cate_id,  String type,  String intro,  String announce){
        mModel.createGroup(name,group_logo,cate_id,type,intro,announce)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 1))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(disposable -> {
//                    if (pullToRefresh)
//                        mRootView.showLoading();//显示下拉刷新的进度条
//                    else
//                        mRootView.loadMore();//显示上拉加载更多的进度条
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
//                    if (pullToRefresh)
//                        mRootView.showLoading();//显示下拉刷新的进度条
//                    else
//                        mRootView.loadMore();//显示上拉加载更多的进度条
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<DataBean>(mErrorHandler) {
                    @Override
                    public void onNext(DataBean data) {
                        System.out.println("GroupOpera  tion");
                        mRootView.hideLoading();//上拉加载更多
                    }
                });
    }


    public  void editGroup(int group_id,  String  name,  int group_logo, String cate_id,   String intro,  String announce ){
        mModel.editGroup(group_id,name,group_logo,cate_id,intro,announce)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 1))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(disposable -> {
//                    if (pullToRefresh)
//                        mRootView.showLoading();//显示下拉刷新的进度条
//                    else
//                        mRootView.loadMore();//显示上拉加载更多的进度条
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
//                    if (pullToRefresh)
//                        mRootView.showLoading();//显示下拉刷新的进度条
//                    else
//                        mRootView.loadMore();//显示上拉加载更多的进度条
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<DataBean>(mErrorHandler) {
                    @Override
                    public void onNext(DataBean data) {
                        System.out.println("GroupOperation");
                        mRootView.hideLoading();//上拉加载更多
                    }
                });
    }

    public  void uploadFiles(List<MultipartBody.Part> filse){
        mModel.uploadFile(filse)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 1))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(disposable -> {
//                    if (pullToRefresh)
//                        mRootView.showLoading();//显示下拉刷新的进度条
//                    else
//                        mRootView.loadMore();//显示上拉加载更多的进度条
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
//                    if (pullToRefresh)
//                        mRootView.showLoading();//显示下拉刷新的进度条
//                    else
//                        mRootView.loadMore();//显示上拉加载更多的进度条
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<DataBean>(mErrorHandler) {
                    @Override
                    public void onNext(DataBean data) {
                        System.out.println("GroupCoverUpload"+data.getData());
                        String logoDataStr = data.getData().toString();
                        JSONArray array = null;
                        int logoData = 0;

                        try {
                             array = new JSONArray(logoDataStr);
                            double d = Double.parseDouble(array.get(0).toString());
                            logoData = (new Double(d).intValue());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


//                        ArrayList<Double> logoDatas = data.getData();
//                        int logoData = (new Double(logoDatas.get(0))).intValue();
                        mRootView.setGroupLogoData(logoData);
                        mRootView.hideLoading();//上拉加载更多
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

}

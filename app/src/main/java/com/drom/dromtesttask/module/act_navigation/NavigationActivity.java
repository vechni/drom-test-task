package com.drom.dromtesttask.module.act_navigation;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.drom.dromtesttask.R;
import com.drom.dromtesttask.common.mvp.BaseActivity;
import com.drom.dromtesttask.common.utils.AppKeys;
import com.drom.dromtesttask.common.view.OnLoadMoreListener;
import com.drom.dromtesttask.module.AppToolbar;
import com.drom.dromtesttask.module.act_navigation.item.NavigationAdapter;
import com.drom.dromtesttask.module.act_navigation.item.RepositoryViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationActivity
        extends BaseActivity
        implements NavigationContract.View
{
    public static final String TAG = NavigationActivity.class.getSimpleName();

    @InjectPresenter( type = PresenterType.LOCAL ) NavigationPresenter presenter;
    @BindView( R.id.act_navigation_rv_repositories ) protected RecyclerView recyclerView;
    @BindView( R.id.act_navigation_txt_warning ) protected TextView tvWarning;
    private NavigationAdapter adapter;
    private SearchView searchView = null;
    private MenuItem itemMenuLogin;
    private MenuItem itemMenuLogOut;
    private boolean isAuthorized;
    private boolean isActiveSearch;
    private String currentParamSearch = null;
    @Inject protected AppToolbar toolbar;

    @Override
    protected void onCreate( @Nullable final Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_navigation);

        ButterKnife.bind(this);

        final Bundle extras = getIntent().getExtras();
        isAuthorized = extras.getBoolean(AppKeys.IS_AUTHORIZED, false);

        if( savedInstanceState != null ){
            currentParamSearch = savedInstanceState.getString(AppKeys.CURRENT_PARAM_QUERY_SEARCH);
            isActiveSearch = savedInstanceState.getBoolean(AppKeys.IS_ACTIVE_SEARCH);
        }

        adapter = new NavigationAdapter();

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new OnLoadMoreListener(layoutManager)
        {
            @Override
            public void onLoadMore( int page, int totalItemsCount, RecyclerView view ){
                presenter.onLoadMore();
            }
        });

        getComponent().inject(this);
    }

    @Override
    protected void onSaveInstanceState( @NonNull final Bundle outState ){
        super.onSaveInstanceState(outState);
        if( searchView.getQuery() != null ){
            outState.putString(AppKeys.CURRENT_PARAM_QUERY_SEARCH, searchView.getQuery().toString());
            outState.putBoolean(AppKeys.IS_ACTIVE_SEARCH, searchView.isIconified());
        }
    }

    @Override
    public boolean onCreateOptionsMenu( @NonNull final Menu menu ){
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        itemMenuLogin = menu.findItem(R.id.menu_toolbar_login);
        itemMenuLogOut = menu.findItem(R.id.menu_toolbar_logout);

        if( isAuthorized ){
            showAuthorisedMenu();
        }else{
            showUnauthorizedMenu();
        }

        setTextChangesListenerOnSearchView(menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( @NonNull final MenuItem item ){
        switch( item.getItemId() ){
            case R.id.menu_toolbar_login:
                presenter.onClickBtnLogIn();
                return true;
            case R.id.menu_toolbar_logout:
                isAuthorized = false;
                presenter.onClickBtnLogOut();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void navigateToLogInScreen(){
        uiRouter.openLogInView();
    }

    @Override
    public void showAuthorisedMenu(){
        itemMenuLogin.setVisible(false);
        itemMenuLogOut.setVisible(true);
    }

    @Override
    public void showUnauthorizedMenu(){
        itemMenuLogin.setVisible(true);
        itemMenuLogOut.setVisible(false);
    }

    @Override
    public void showWaitDialog(){
        final String message = this.getString(R.string.txt_wait);
        openWaitDialog(message, null);
    }

    @Override
    public void hideWaitDialog(){
        closeWaitDialog();
    }

    @Override
    public void showMessage( @StringRes final int resId ){
        showToastShort(resId);
    }

    @Override
    public void showMessage( @NonNull String message ){
        showToastShort(message);
    }

    @Override
    public void showWarning( @StringRes final int resId ){
        recyclerView.setAdapter(null);
        tvWarning.setText(getString(resId));
        tvWarning.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideWarning(){
        tvWarning.setVisibility(View.GONE);
    }

    @Override
    public void updateRepositories( @NonNull final List<RepositoryViewModel> list ){
        adapter.setItems(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateNextRepositories( @NonNull final List<RepositoryViewModel> list ){
        adapter.addItems(list);
        adapter.notifyItemInserted(adapter.getItemCount());
    }

    private void setTextChangesListenerOnSearchView( @NonNull final Menu menu ){
        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.menu_toolbar_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        if( currentParamSearch != null ){
            searchView.setIconified(isActiveSearch);
            searchView.setQuery(currentParamSearch, true);
            searchView.clearFocus();
        }

        presenter.onChangeSearchView(searchView);
    }
}


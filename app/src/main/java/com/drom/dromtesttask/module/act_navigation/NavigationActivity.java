package com.drom.dromtesttask.module.act_navigation;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.drom.dromtesttask.common.view.EndlessRecyclerViewScrollListener;
import com.drom.dromtesttask.model.RepositoryItem;
import com.drom.dromtesttask.model.StateEndlessRecycler;
import com.drom.dromtesttask.module.act_log_in.LogInActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationActivity extends BaseActivity implements NavigationContract.View {

    public static final String TAG = "tag_main_act";
    public static final String CURRENT_PARAM_QUERY_SEARCH_KEY = "current_param_query_search_key";
    public static final String IS_ICONIFIED_SEARCH_VIEW_KEY = "is_iconified_search_view_key";
    public static final String IS_FOCUSED_SEARCH_VIEW_KEY = "is_focused_search_view_key";
    public static final String CURRENT_PAGE_KEY = "current_page_key";

    @BindView(R.id.act_navigation_rv_repositories) RecyclerView rvRepositories;
    @BindView(R.id.act_navigation_txt_warning) TextView txtWarning;
    private MenuItem itemMenuLogin;
    private MenuItem itemMenuLogOut;
    private List<RepositoryItem> list;
    private NavigationAdapter adapter;
    private EndlessRecyclerViewScrollListener endlessScrollListener;
    private StateEndlessRecycler stateEndlessRecycler = null;
    private boolean isRotateScreen = true;
    private SearchView searchView = null;
    private boolean isIconifiedSearchView;
    private String paramSearch = null;

    @InjectPresenter(type = PresenterType.LOCAL) NavigationPresenter presenter;

    // region - Lifecycle -

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_navigation);

        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            paramSearch = savedInstanceState.getString(CURRENT_PARAM_QUERY_SEARCH_KEY);
            isIconifiedSearchView = savedInstanceState.getBoolean(IS_ICONIFIED_SEARCH_VIEW_KEY);
            stateEndlessRecycler = savedInstanceState.getParcelable(CURRENT_PAGE_KEY);
        }

        initUix();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (searchView.getQuery() != null) {
            String query = searchView.getQuery().toString();
            boolean isIconified = searchView.isIconified();
            outState.putString(CURRENT_PARAM_QUERY_SEARCH_KEY, query);
            outState.putBoolean(IS_ICONIFIED_SEARCH_VIEW_KEY, isIconified);
        }
        StateEndlessRecycler state = endlessScrollListener.getCurrentState();
        outState.putParcelable(CURRENT_PAGE_KEY, state);
    }

    // endregion


    // region - Event Menu -

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);

        itemMenuLogin = menu.findItem(R.id.menu_toolbar_login);
        itemMenuLogOut = menu.findItem(R.id.menu_toolbar_logout);

        presenter.checkAuth();

        setTextChangesListenerOnSearchView(menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_toolbar_login:
                navigateToLogInScreen();
                return true;
            case R.id.menu_toolbar_logout:
                presenter.logOut();
                return true;
            default:
                return false;
        }
    }

    // endregion


    // region - Navigation -

    public void navigateToLogInScreen() {
        Intent mainIntent = new Intent(NavigationActivity.this, LogInActivity.class);
        startActivity(mainIntent);
        finish();
    }

    // endregion


    // region - Event handlers -

    // endregion


    // region - Contract -

    @Override
    public void showAuthorisedMenuToolbar() {
        itemMenuLogin.setVisible(false);
        itemMenuLogOut.setVisible(true);
    }

    @Override
    public void showNotAuthorisedMenuToolbar() {
        itemMenuLogin.setVisible(true);
        itemMenuLogOut.setVisible(false);
    }

    @Override
    public void startWaitDialog() {
        String message = this.getString(R.string.txt_wait);
        openWaitDialog(message, null);
    }

    @Override
    public void finishWaitDialog() {
        closeWaitDialog();
    }

    @Override
    public void showMessage(String message) {
        showToastShort(message);
    }

    @Override
    public void showWarning(String message) {
        rvRepositories.setAdapter(null);
        txtWarning.setText(message);
        txtWarning.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSearchResult(List<RepositoryItem> list) {
        hideWarning();

        this.list = list;

        if (isRotateScreen) isRotateScreen = false;
        else endlessScrollListener.resetState();

        adapter = new NavigationAdapter(this, list);
        rvRepositories.setAdapter(adapter);
    }

    @Override
    public void addLoadedData(List<RepositoryItem> list) {
        this.list.addAll(list);
        int fromNumber = adapter.getItemCount();
        adapter.notifyItemInserted(fromNumber);
    }

    // endregion


    // region - Methods -

    private void initUix() {
        initToolbar();
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutMgr = new LinearLayoutManager(this);

        endlessScrollListener = new EndlessRecyclerViewScrollListener(layoutMgr) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.loadNextData(page + 1);
            }
        };

        if (stateEndlessRecycler != null) {
            endlessScrollListener.setCurrentState(stateEndlessRecycler);
        }

        rvRepositories.setLayoutManager(layoutMgr);
        rvRepositories.addOnScrollListener(endlessScrollListener);
    }

    private void hideWarning() {
        txtWarning.setVisibility(View.GONE);
    }

    private void setTextChangesListenerOnSearchView(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.menu_toolbar_search);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        }

        if (paramSearch != null) {
            searchView.setIconified(isIconifiedSearchView);
            searchView.setQuery(paramSearch, true);
            searchView.clearFocus();
        }

        presenter.setTextChangesListenerOnSearchView(searchView);
    }

    // endregion
}


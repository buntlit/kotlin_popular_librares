package com.buntlit.githubclient.mvp.di

import com.buntlit.githubclient.mvp.di.modules.*
import com.buntlit.githubclient.mvp.presenter.MainPresenter
import com.buntlit.githubclient.mvp.presenter.RepositoryPresenter
import com.buntlit.githubclient.mvp.presenter.UserRepositoriesPresenter
import com.buntlit.githubclient.mvp.presenter.UsersPresenter
import com.buntlit.githubclient.ui.MainActivity
import com.buntlit.githubclient.ui.adapter.UsersRVAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CiceroneModule::class,
        DatabaseModule::class,
        RepoModule::class]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRepositoryPresenter: UserRepositoriesPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}
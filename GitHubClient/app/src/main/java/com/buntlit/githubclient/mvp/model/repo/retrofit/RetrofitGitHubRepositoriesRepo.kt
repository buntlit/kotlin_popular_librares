package com.buntlit.githubclient.mvp.model.repo.retrofit

import com.buntlit.githubclient.mvp.model.api.IDataSource
import com.buntlit.githubclient.mvp.model.entity.GitHubRepository
import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import com.buntlit.githubclient.mvp.model.entity.room.Database
import com.buntlit.githubclient.mvp.model.entity.room.RoomGitHubRepository
import com.buntlit.githubclient.mvp.model.repo.IGitHubRepositoriesRepo
import com.buntlit.githubclient.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGitHubRepositoriesRepo(
    private val api: IDataSource,
    private val networkStatus: AndroidNetworkStatus,
    private val db: Database
) :
    IGitHubRepositoriesRepo {
    override fun getRepositories(user: GitHubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl.let { url ->
                    api.getRepositories(url!!).flatMap { repositories ->
                        Single.fromCallable {
                            val roomUser = user.login.let { db.userDao.findByLogin(it) }
                                ?: throw Exception("No such user in cache")
                            val roomRepositories = repositories.map {
                                RoomGitHubRepository(
                                    it.id ?: "",
                                    it.name ?: "",
                                    it.forksCount ?: 0,
                                    roomUser.id ?:""
                                )
                            }
                            db.repositoryDao.insert(roomRepositories)
                            repositories
                        }

                    }
                }

            } else {
                Single.fromCallable {
                    val roomUser = user.login.let { db.userDao.findByLogin(it) }
                        ?: throw Exception("No such user in cache")
                    db.repositoryDao.findByUserId(roomUser.id)
                        .map { GitHubRepository(it.id, it.name, it.forksCount) }
                }
            }
        }.subscribeOn(Schedulers.io())
}
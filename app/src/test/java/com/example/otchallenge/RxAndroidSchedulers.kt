package com.example.otchallenge

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.rules.ExternalResource
import org.junit.rules.TestRule


/**
 * A JUnit [TestRule] that resets all [Schedulers] before and after each test run.
 *
 */
class RxAndroidSchedulers : ExternalResource() {


    override fun before() = reset()

    override fun after() = reset()

    fun reset() {
        // reset the hooks we've created
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    /**
     * Allows you to override the [AndroidSchedulers.mainThread] scheduler.
     */
    fun setMainThreadScheduler(
        mainThreadScheduler: Scheduler
    ): RxAndroidSchedulers {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { mainThreadScheduler }
        return this
    }

    /** Allows you to override the [Schedulers.computation] scheduler */
    fun setIoScheduler(ioScheduler: Scheduler): RxAndroidSchedulers {
        RxJavaPlugins.setIoSchedulerHandler { ioScheduler }
        return this
    }

    /** Allows you to override the [Schedulers.computation] scheduler */
    fun setComputationScheduler(computationScheduler: Scheduler): RxAndroidSchedulers {
        RxJavaPlugins.setComputationSchedulerHandler { computationScheduler }
        return this
    }

    /** Allows you to override the [Schedulers.newThread] scheduler */
    fun setNewThreadScheduler(newThreadScheduler: Scheduler): RxAndroidSchedulers {
        RxJavaPlugins.setNewThreadSchedulerHandler { newThreadScheduler }
        return this
    }
}

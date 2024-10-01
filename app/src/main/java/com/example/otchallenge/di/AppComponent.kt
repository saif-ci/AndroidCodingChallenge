package com.example.otchallenge.di

import android.app.Application
import com.example.otchallenge.MainActivity
import com.example.otchallenge.di.scopes.AppScope
import com.example.otchallenge.di.scopes.SingleIn
import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
interface AppComponent {
	fun inject(activity: MainActivity)

	@MergeComponent.Builder
	interface Builder {
		@BindsInstance
		fun application(application: Application): Builder

		fun build(): AppComponent
	}
}

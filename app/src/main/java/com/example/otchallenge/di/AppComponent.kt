package com.example.otchallenge.di

import com.example.otchallenge.MainActivity
import com.example.otchallenge.di.scopes.AppScope
import com.example.otchallenge.di.scopes.SingleIn
import com.squareup.anvil.annotations.MergeComponent
import dagger.Component
import javax.inject.Singleton

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
interface AppComponent {
	fun inject(activity: MainActivity)
}

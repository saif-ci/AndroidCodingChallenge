package com.example.otchallenge.core.mvp

/**
 * This provides a contract for handling attach and detach of the view in the presenter that is
 * called from the [android.app.Activity] or [androidx.fragment.app.Fragment] Used to attach views
 * or null down views as and when necessary.(Example : Across rotation of the activity)
 */
interface BasePresenter<T> {
  /**
   * Used to attach the presenter to the view and indicate to the presenter that the view is ready
   * to receive data from it.
   *
   * @param view
   */
  fun attach(view: T)

  /**
   * Used to detach the view from the presenter and indicate to the presenter that the view is no
   * longer available to receive data from it.
   */
  fun detach()
}
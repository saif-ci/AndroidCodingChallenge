package com.example.otchallenge.repository.api.model

sealed interface RepositoryResult<out Success : Any, out Error : Any> {
    data class Success<Success : Any>(val value: Success) : RepositoryResult<Success, Nothing>

    data class Failure<Error : Any>(val failure: Error) : RepositoryResult<Nothing, Error>
}
package com.example.moviegrid.domain


abstract class UseCaseResult<in Params, Any : kotlin.Any> internal constructor(
) {

    internal abstract suspend fun buildObservable(params: Params?):Result<Any>

    suspend fun execute(params: Params? = null): Result<Any> {
        return buildObservable(params)

    }
    class None
}
package com.megane.domain.usecase

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Resource<R>].
 */
abstract class FlowUseCase<in P, out R> constructor(
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(param: P): Flow<R> = execute(param)
        .catch { e -> Log.e(FlowUseCase::class.java.name, "$e") }
        .flowOn(dispatcher)

    protected abstract fun execute(param: P): Flow<R>
}

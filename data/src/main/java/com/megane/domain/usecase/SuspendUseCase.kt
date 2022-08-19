package com.megane.domain.usecase

import android.util.Log
import com.megane.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class SuspendUseCase<in Param, out Result> constructor(
    private val dispatcher: CoroutineDispatcher
) {

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(p: Param): Result

    suspend operator fun invoke(p: Param): Resource<Result> {
        return try {
            withContext(dispatcher) {
                execute(p).let {
                    Resource.Success(it)
                }
            }
        } catch (e: Exception) {
            Log.e(SuspendUseCase::class.java.name, "$e")
            Resource.Error(e)
        }
    }
}

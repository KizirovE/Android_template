package kz.kizirov.core.domain

data class ResultUC<T>(
    private val successBody: T?,
    private val failedBody: Failed?
){
    val isSuccessfull: Boolean = successBody != null
    val isFailed: Boolean = failedBody != null

    val body
        get() =  if(successBody != null) successBody else throw NullPointerException("you mast use isSuccessfull before call body")
    val failed
        get() =  if(failedBody != null) failedBody else throw NullPointerException("you mast use isFailed before call failed")

    data class Failed(
        val message: String,
    )
}
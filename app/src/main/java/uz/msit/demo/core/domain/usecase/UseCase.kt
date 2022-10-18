package uz.msit.demo.core.domain.usecase

abstract class UseCase<in P, R> {

    /** Executes the use case asynchronously and returns a [Result].
     *
     * @return a [Result].
     *
     * @param parameters the input parameters to run the use case with
     */
    abstract suspend operator fun invoke(parameters: P): Result<R>
}
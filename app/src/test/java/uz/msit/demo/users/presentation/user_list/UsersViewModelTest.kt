package uz.msit.demo.users.presentation.user_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import uz.msit.demo.core.utils.Left
import uz.msit.demo.core.utils.Right
import uz.msit.demo.users.MainCoroutineRule
import uz.msit.demo.users.domain.failure.GetUsersFailure
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.domain.repository.UserRepository

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class UsersViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mockRepository: UserRepository
    private lateinit var fakeUsersUseCase: FakeGetUsersUseCase

    private lateinit var sut: UsersViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        fakeUsersUseCase = FakeGetUsersUseCase(mockRepository)
        sut= UsersViewModel(fakeUsersUseCase)
    }

    @Test
    fun `viewModel shows initial state`() = runTest {
        sut = UsersViewModel(fakeUsersUseCase)
        val expectedState = UsersState(isLoading = true)

        // Get the initial state
        val state = sut.state.value

        assertEquals(expectedState, state)
    }

    @Test
    fun `viewModel shows users state when result success`() = runTest {

        val users = listOf(User(1, "sasa", "vava"), User(2, "vava", "dada"))

        // Get the initial state

        launch {
            fakeUsersUseCase.emit(Right(users))
        }.join()

        assertEquals(UsersState(users), sut.state.value)
    }

    @Test
    fun `viewModel shows error state when result error`() = runTest {
        val expectedState = UsersState(isLoading = false, message = "No network connection!")

        launch {
            fakeUsersUseCase.emit(Left(GetUsersFailure.UnknownError))
        }.join()

        assertEquals(expectedState, sut.state.value)
    }
}
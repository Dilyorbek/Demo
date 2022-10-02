package uz.msit.demo.users.presentation.user_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
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
import uz.msit.demo.users.domain.repository.UserRepository
import uz.msit.demo.users.presentation.user_list.FakeGetUsersUseCase
import uz.msit.demo.users.presentation.user_list.UsersState
import uz.msit.demo.users.presentation.user_list.UsersViewModel
import uz.msit.demo.utils.TestData

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class UserDetailsViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mockRepository: UserRepository
    private lateinit var fakeGetUserDetailsUseCase: FakeGetUserDetailsUseCase
    private lateinit var sut: UserDetailsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        fakeGetUserDetailsUseCase = FakeGetUserDetailsUseCase(mockRepository)
        val savedStateHandle = SavedStateHandle().apply {
            set("login", TestData.testUserDetails.login)
        }
        sut = UserDetailsViewModel(fakeGetUserDetailsUseCase, savedStateHandle)
    }

    @Test
    fun `did shows initial state`() = runTest {
        val expectedState = UserDetailsState(isLoading = true)

        assertEquals(expectedState, sut.state.value)
    }

    @Test
    fun `did shows details when result success`() = runTest {
        val expectedState = UserDetailsState(TestData.testUserDetails)

        launch { fakeGetUserDetailsUseCase.emit(Right(TestData.testUserDetails)) }.join()

        assertEquals(expectedState, sut.state.value)
    }

    @Test
    fun `did shows error state when result failure`() = runTest {
        val expectedState = UserDetailsState(message = "No network connection!")

        launch { fakeGetUserDetailsUseCase.emit(Left(GetUsersFailure.UnknownError)) }.join()

        assertEquals(expectedState, sut.state.value)
    }
}
package uz.msit.demo.feature.users.presentation.user_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
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
import uz.msit.demo.feature.users.MainCoroutineRule
import uz.msit.demo.feature.users.domain.failure.GetUsersFailure
import uz.msit.demo.feature.users.domain.repository.UserRepository
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

        fakeGetUserDetailsUseCase.emit(Result.success(TestData.testUserDetails))

        launch { sut.onEvent(UserDetailsEvent.GetUserDetails) }.join()


        assertEquals(expectedState, sut.state.value)
    }

    @Test
    fun `did shows error state when result failure`() = runTest {
        val expectedState = UserDetailsState(message = "No network connection!")

        fakeGetUserDetailsUseCase.emit(Result.failure(GetUsersFailure.UnknownError))

        launch {
            sut.onEvent(UserDetailsEvent.GetUserDetails)
        }.join()

        assertEquals(expectedState, sut.state.value)
    }
}
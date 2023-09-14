package com.example.assignment_2
import androidx.lifecycle.Observer
import com.example.assignment_2.fragment.FragmentAllList
import com.example.assignment_2.fragment.FragmentStaffList
import com.example.assignment_2.fragment.FragmentStudentList
import com.example.assignment_2.model.ActorsDetails
import com.example.assignment_2.module.AppModule
import com.example.assignment_2.utils.APIConsumer
import com.example.assignment_2.viewModel.ActorsDetailsViewModel
import com.example.assignment_2.viewModel.StaffViewModel
import com.example.assignment_2.viewModel.StudentViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
@UninstallModules(AppModule::class)
class ActorsTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var apiConsumer: APIConsumer

    private lateinit var actorsDetailsViewModel: ActorsDetailsViewModel
    private lateinit var staffViewModel: StaffViewModel
    private lateinit var studentViewModel: StudentViewModel

    private lateinit var fragmentAllList: FragmentAllList
    private lateinit var fragmentStaffList: FragmentStaffList
    private lateinit var fragmentStudentList: FragmentStudentList

    @Before
    fun init() {
        hiltRule.inject()
        actorsDetailsViewModel = ActorsDetailsViewModel(apiConsumer)
        studentViewModel = StudentViewModel(apiConsumer)
        staffViewModel = StaffViewModel(apiConsumer)
    }

    @Test
    fun testFetchActorsDataSuccess() {
        val receive = Observer<ActorsDetails?> { actors ->
            assertNotNull(actors)
            assertEquals(fragmentAllList.pageSize, actors?.size)
        }
        actorsDetailsViewModel.fetchActors()
    }

    @Test
    fun testFetchActorsDataFailure() {
        val receive = Observer<ActorsDetails?> { actors ->
            assertNotNull(actors)
            assertEquals(fragmentAllList.pageSize - 1, actors?.size)
        }
        actorsDetailsViewModel.fetchActors()
    }

    @Test
    fun testFetchStaffDataSuccess() {
        val receive = Observer<ActorsDetails?> { staff ->
            assertNotNull(staff)
            //assertEquals(fragmentStaffList.allStaffs, staff?.size)
            assertEquals(fragmentStaffList.pageSize,staff?.size)
        }
        staffViewModel.fetchStaff()
    }



    @Test
    fun testFetchStaffDataFailure() {
        val receive = Observer<ActorsDetails?> { staff ->
            assertNotNull(staff)
           // assertEquals(fragmentStaffList.allStaffs - 1, staff?.size)
            assertEquals(fragmentStaffList.pageSize-1,staff?.size)
        }
        staffViewModel.fetchStaff()
    }

    @Test
    fun testFetchStudentsDataSuccess() {
        val receive = Observer<ActorsDetails?> { students ->
            assertNotNull(students)
            assertEquals(fragmentStudentList.pageSize, students?.size)
        }
       studentViewModel.fetchStudents()
    }

    @Test
    fun testFetchStudentsDataFailure() {
        val receive = Observer<ActorsDetails?> { students ->
            assertNotNull(students)
            assertEquals(fragmentStudentList.pageSize - 1, students?.size)
        }
        studentViewModel.fetchStudents()
    }
}
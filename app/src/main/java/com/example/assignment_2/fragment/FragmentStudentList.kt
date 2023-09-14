package com.example.assignment_2.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_2.R
import com.example.assignment_2.model.ActorsDetails
import com.example.assignment_2.utils.ActorsAdapter
import com.example.assignment_2.viewModel.StaffViewModel
import com.example.assignment_2.viewModel.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentStudentList : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ActorsAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var progressBar: ProgressBar
    private val viewModel: StudentViewModel by viewModels()


    var pageSize = 1
    var isLoading = false
    private val pageLimit = 20

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewStudentsLists)
        progressBar = view.findViewById(R.id.progressBar)
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        adapter = ActorsAdapter(childFragmentManager)
        recyclerView.adapter = adapter

        @SuppressLint("NotifyDataSetChanged")
        fun getData(studentsList: ActorsDetails) {
            isLoading = true
            progressBar.visibility = View.VISIBLE
            val start = (pageSize - 1) * pageLimit
            val end = (pageSize) * pageLimit

            for (i in start..end) {
                adapter.setData(studentsList.take(i))
            }

            Handler().postDelayed({
                if (::adapter.isInitialized) {
                    adapter.notifyDataSetChanged()
                } else {
                    adapter = ActorsAdapter(childFragmentManager)
                    recyclerView.adapter = adapter
                }
                isLoading = false
                progressBar.visibility = View.VISIBLE
            }, 5000)
        }

        viewModel.studentsLiveData.observe(viewLifecycleOwner) { students ->
            students?.let { studentsList ->
                getData(studentsList)
                recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        if (dy > 0) {
                            val visibileItemCount = layoutManager.childCount
                            val pastVisibleItem =
                                layoutManager.findFirstCompletelyVisibleItemPosition()
                            val  allStudents = adapter.itemCount

                            if (!isLoading) {
                                if ((visibileItemCount + pastVisibleItem) >= allStudents) {
                                    pageSize++
                                    getData(studentsList)
                                }
                            }
                        }
                        super.onScrolled(recyclerView, dx, dy)
                    }
                })
            }
        }
        return view
    }


}
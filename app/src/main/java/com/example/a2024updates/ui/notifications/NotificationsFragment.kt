package com.example.a2024updates.ui.notifications

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a2024updates.R
import com.example.a2024updates.databinding.FragmentNotificationsBinding
import com.example.a2024updates.ui.notifications.indetail.MyDialogFragment
import com.example.a2024updates.ui.notifications.indetail.NotifyAdapter
import com.example.a2024updates.ui.notifications.roomdb.Task
import com.example.a2024updates.ui.notifications.roomdb.TaskDao
import com.example.a2024updates.ui.notifications.roomdb.TaskDatabase
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class NotificationsFragment : Fragment(), MyDialogFragment.DialogListener {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var notifyAdapter: NotifyAdapter? = null
    private var taskList = arrayListOf<Task>()
    private var taskTitle: String? = null
    private var taskTime: String? = null
    private var taskImage = 0
    private var taskDone = false

    var notifyRecyclerView: RecyclerView? = null

    private var taskDao: TaskDao? = null
    private var taskDatabase: TaskDatabase? = null
    private var isGranted = false
    var listOfDates = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        checkAndRequestNotificationPermission()

        val textView: TextView = binding.textNotifications
        notifyRecyclerView = binding.notifyRecyclerView
        val taskBtn = binding.addTaskBtn

        taskDatabase = TaskDatabase.getInstance(requireContext())
        taskDao = taskDatabase?.taskDao()

        taskList = ArrayList()
        listOfDates = ArrayList()
        notifyAdapter = NotifyAdapter(requireContext(), taskList as ArrayList<Task>)
        notifyRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        notifyRecyclerView?.adapter = notifyAdapter


        getAllTasks()


        taskBtn.setOnClickListener {

            val dialogFragment = MyDialogFragment()
            dialogFragment.setDialogListener(this)
            dialogFragment.show(childFragmentManager, "MyDialogFragment")
        }

        notificationsViewModel.getDialogDataText().observe(viewLifecycleOwner) { dialogTexts ->
            taskTitle = dialogTexts.title
            taskTime = dialogTexts.time
            taskImage = dialogTexts.image
            taskDone = dialogTexts.taskDone

            taskImage = dialogTexts.image
            if (taskImage == 0) {
                taskImage = R.drawable.ic_notifications_black_24dp
            }
            Toast.makeText(requireContext(), "$taskTime $taskImage", Toast.LENGTH_SHORT).show()
            taskTitle?.let { title ->
                taskTime?.let { time ->
                    val task =
                        Task(title, time, taskImage, taskDone)
                            //R.drawable.ic_notifications_black_24dp, false)
                    taskList.add(task)
                    listOfDates.add(taskTime!!)
                    notifyAdapter?.notifyDataSetChanged()
                    taskDao?.insert(task)
                }
            }
        }

        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }


        return root
    }

    private fun getAllTasks() {
        Log.d("revathi", "getAllTasks: ")
        taskDao?.getAllTasks()?.let {
            taskList.addAll(it)
            listOfDates.addAll(it.map { task -> task.timeText })
            Log.d("revathi", "getAllTasks: $listOfDates")
            if (isGranted) {
                setNotificationForDates(listOfDates)
                //showNotification1(title, time, taskImage)
            }
            notifyAdapter?.notifyDataSetChanged()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDataPassed(data: String, data1: String, dataImage: Int, taskDone: Boolean) {
        notificationsViewModel.setDialogData(data, data1, dataImage, taskDone)
    }

    private fun checkAndRequestNotificationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                101
            )
        }
        isGranted = true
    }

  /*  private fun showNotification1(title: String, time: String, image:Int) {
        val mChannel = NotificationChannelCompat.Builder(channelId, importance).apply {
            setName("channel name") // Must set! Don't remove
            setDescription("channel description")
            setLightsEnabled(true)
            setLightColor(Color.RED)
            setVibrationEnabled(true)
            setVibrationPattern(longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400))
        }.build()


        NotificationManagerCompat.from(requireContext()).createNotificationChannel(mChannel)
        val notification: Notification = NotificationCompat.Builder(requireContext(), channelId)
            .setSmallIcon(taskImage) //R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(time)
            .build()
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                101
            )
        }
        NotificationManagerCompat.from(requireContext()).notify(notifyID, notification)
    }

    //handle the result of the permission request
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            101 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, proceed with your code
                    Toast.makeText(requireContext(), "Permission granted", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(requireContext(), "permission denied", Toast.LENGTH_SHORT).show()
                    // Permission denied, handle accordingly (e.g., show a message to the user)
                }
            }
            else -> {
                // Handle other permission requests if needed
            }
        }
    }

    private fun timeCheckWithSelectedDate() {

        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = simpleDateFormat.parse(taskTime!!)
        val currentTime = currentDate?.time

        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd/MM/yyyy") //or use getDateInstance()
        val formatedDate = formatter.format(date)
//    val formatTime = formatter.format("HH:mm:ss")

        //   val currentDate1 = LocalDate.now(ZoneId.systemDefault())
        // val currentDate2 = SimpleDateFormat("dd/MM/yyyy")//simpleDateFormat.parse(currentDate1.toString())
        if (taskTime.equals(formatedDate)) {
            Toast.makeText(requireContext(), "Time is today  $taskTime", Toast.LENGTH_SHORT).show()
            val time = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            Toast.makeText(requireContext(), "Time is today  $time", Toast.LENGTH_SHORT).show()
            if (time in 19..20) {
                Toast.makeText(requireContext(), "hour is now $taskTime", Toast.LENGTH_SHORT).show()
            }

        }
        /*  if (System.currentTimeMillis() > currentTime!!) {
                  Toast.makeText(requireContext(), "Time is now$taskTime", Toast.LENGTH_SHORT).show()

              Toast.makeText(requireContext(), "Time is up$formatedDate $currentDate", Toast.LENGTH_SHORT).show()
          } else {
              Toast.makeText(requireContext(), "Time is not up", Toast.LENGTH_SHORT).show()
          }*/

    }
   */

    private fun setNotificationForDates(dates: List<String>) {
        Log.d("revathi", "setNotificationForDates: $dates")
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(requireContext(), NotificationReceiver::class.java).let {intent->
            PendingIntent.getBroadcast(requireContext(), 0, intent,
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_MUTABLE else 0)
        }

        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        for(dateString in dates) {
            try {
                val date = dateFormatter.parse(dateString)
                val calendar = Calendar.getInstance().apply {
                    time = date
                    set(Calendar.HOUR_OF_DAY, 9)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                }

                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    alarmIntent
                )
                Log.d("revathi", "setNotificationForDates: ${calendar.time}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
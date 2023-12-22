package com.example.a2024updates.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text


    private val dialogLiveTextData = MutableLiveData<DialogTexts>()
    fun setDialogData(data: String, data1: String, dataImage: Int, taskDone: Boolean) {
        val textData = DialogTexts(data,data1,dataImage,taskDone)
        dialogLiveTextData.value = textData
    }

    fun getDialogDataText(): LiveData<DialogTexts> {
        return dialogLiveTextData
    }

    data class DialogTexts(
        val title:String,
        val time:String,
        val image:Int,
        val taskDone: Boolean
    )
}
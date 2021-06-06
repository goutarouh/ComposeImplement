package countdown.viewmodel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import countdown.helper.SingleLiveEvent
import countdown.utils.Utility
import countdown.utils.Utility.formatTime

class CountdownViewModel: ViewModel() {

    private var countDownTimer: CountDownTimer? = null

    private val _time = MutableLiveData(Utility.TIME_COUNTDONW.formatTime())
    val time: LiveData<String> = _time

    private val _progress = MutableLiveData(1.00F)
    val progress: LiveData<Float> = _progress

    private val _isPlaying = MutableLiveData(false)
    val isPlaying: LiveData<Boolean> = _isPlaying

    private val _celebrate = SingleLiveEvent<Boolean>()
    val celebrate: LiveData<Boolean> get() = _celebrate


    fun handleCountDownTimer() {
        if (isPlaying.value == true) {
            pauseTimer()
            _celebrate.postValue(false)
        } else {
            startTimer()
        }
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
        handleTimerValues(false, Utility.TIME_COUNTDONW.formatTime(), 1.0F, false)
    }


    private fun startTimer() {
        _isPlaying.value = true
        countDownTimer = object: CountDownTimer(Utility.TIME_COUNTDONW, 1000) {

            override fun onTick(millisRemaining: Long) {
                val progressValue = millisRemaining.toFloat() / Utility.TIME_COUNTDONW
                handleTimerValues(true, millisRemaining.formatTime(), progressValue, false)
                _celebrate.postValue(false)
            }

            override fun onFinish() {
                Log.i("hasegawa", "onFinish")
                pauseTimer()
                _celebrate.postValue(true)
            }

        }.start()
    }

    private fun handleTimerValues(isPlaying: Boolean, text: String, progress: Float, celebrate: Boolean) {
        _isPlaying.value = isPlaying
        _time.value = text
        _progress.value = progress
        _celebrate.postValue(celebrate)
    }

}
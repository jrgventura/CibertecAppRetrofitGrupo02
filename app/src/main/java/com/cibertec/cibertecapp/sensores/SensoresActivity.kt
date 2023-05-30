package com.cibertec.cibertecapp.sensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.cibertecapp.R

class SensoresActivity: AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var mLight: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensores)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)


        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        Log.v("LISTADO_SENSORES", deviceSensors.toString())

        val sensorText = StringBuilder()
        for (sensor in deviceSensors) {
            sensorText.append(sensor.name).append(
                System.getProperty("line.separator")
            )
        }
        val sensorTextView = findViewById<TextView>(R.id.textSensores)
        sensorTextView.text = sensorText

        mLight?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }

    }

    // Los valores que el sensor reporta
    override fun onSensorChanged(sensor: SensorEvent) {
        val value = sensor.values[0]
        Log.v("SENSOR_LUZ", value.toString())
    }

    // La precisión del sensor
    override fun onAccuracyChanged(sensor: Sensor, precision: Int) {
        Log.v("SENSOR_LUZ", sensor.toString())
        Log.v("SENSOR_LUZ", sensor.name.toString())
        Log.v("SENSOR_LUZ", precision.toString())
    }

}
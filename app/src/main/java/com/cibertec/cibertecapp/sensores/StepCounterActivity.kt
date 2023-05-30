package com.cibertec.cibertecapp.sensores

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.cibertec.cibertecapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StepCounterActivity: AppCompatActivity(), SensorEventListener {

    private lateinit var textContador: TextView
    private lateinit var floatingIniciar: FloatingActionButton

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step_counter)

        textContador = findViewById(R.id.textContador)
        floatingIniciar = findViewById(R.id.floatingIniciar)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)


        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
            Log.v("VERIFICAR_SENSOR", "SENSOR EXISTE")
        } else {
            Log.v("VERIFICAR_SENSOR", "SENSOR NO EXISTE")
        }

        floatingIniciar.setOnClickListener {

            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACTIVITY_RECOGNITION) ==
                    PackageManager.PERMISSION_GRANTED) {
                counter = 0
                sensor?.also {
                    sensorManager.registerListener(this, it,
                        SensorManager.SENSOR_DELAY_FASTEST)
                }
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACTIVITY_RECOGNITION),
                    100)
            }



        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 100){

            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                counter = 0
                sensor?.also {
                    sensorManager.registerListener(this, it,
                        SensorManager.SENSOR_DELAY_FASTEST)
                }

            } else {
                // le muestro un error
            }

        }


    }

    override fun onSensorChanged(evento: SensorEvent) {
       val pasos = evento.values[0]
        counter += pasos.toInt()
        textContador.text = counter.toString()
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

}
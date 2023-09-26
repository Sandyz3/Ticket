package com.example.ticket

import android.annotation.SuppressLint
import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.ticket.databinding.ActivityFormBinding
import java.sql.Time
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    private val jenis_tiket = arrayOf(
        "Ekonomi",
        "Bisnis",
        "Kelas Pertama"
    )
    companion object{
        var asal = "asal"
        var tujuan = "tujuan"
        var tanggal = "tanggal"
        var jam = "tahun"
        var jenis = "menit"
    }

    private  val kalender = Calendar.getInstance()
    private val waktu = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var kalender = Calendar.getInstance()

        with(binding){

            val adapterJenisTiket = ArrayAdapter(this@FormActivity, R.layout.simple_spinner_item, jenis_tiket);
            adapterJenisTiket.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            spinerJenistiket.adapter= adapterJenisTiket

            spinerJenistiket.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent : AdapterView<*>, view: View, position: Int, id: Long) {
                    val selectedItemPos = parent.getItemAtPosition(position).toString()
                    jenis = selectedItemPos
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            buttonTanggal.setOnClickListener{
                showDatePicker()
            }

            buttonWaktu.setOnClickListener{
                showTimePicker()
            }

            buttonConfirm.setOnClickListener {
                if(asalText.text.toString() != "" && tujuanText.text.toString() != "" && desc1.text != "DD / MM / YY" && desc2.text!="HH : MM"){
                    val intentConfirm = Intent(this@FormActivity, ConfirmActivity::class.java)
                    intentConfirm.putExtra(asal, asalText.text.toString())
                    intentConfirm.putExtra(tujuan, tujuanText.text.toString())
                    intentConfirm.putExtra(tanggal, desc1.text)
                    intentConfirm.putExtra(jam, desc2.text)
                    intentConfirm.putExtra(jenis, jenis)
                    startActivity(intentConfirm)
                }else{
                    Toast.makeText(this@FormActivity, "KOLOM TIDAK BOLEH KOSONG !", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showDatePicker(){
        val datePickerDialog = DatePickerDialog(this, {DatePicker, year: Int, montOfYear: Int, dayOfMonth: Int ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, montOfYear, dayOfMonth)
            val dateformat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val formatedDate = dateformat.format(selectedDate.time)
            binding.desc1.text = formatedDate

        },
            kalender.get(Calendar.YEAR),
            kalender.get(Calendar.MONTH),
            kalender.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun showTimePicker(){
        val jam = waktu.get(Calendar.HOUR_OF_DAY)
        val menit = waktu.get(Calendar.MINUTE)
        val simpleTimePicker = TimePickerDialog(this, { _, hourOfDay: Int, minute :Int ->
            timeFormat(hourOfDay, minute)
        }, jam, menit, false)
        simpleTimePicker.show()
    }

    private fun timeFormat(hourOfDay: Int, minute: Int) {
        val numberFormat = DecimalFormat("00")
        val formatedHour = numberFormat.format(hourOfDay)
        val formatedMin = numberFormat.format(minute)
        binding.desc2.text = "$formatedHour : $formatedMin"

    }

}
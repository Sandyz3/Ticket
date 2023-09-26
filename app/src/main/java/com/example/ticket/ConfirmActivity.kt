package com.example.ticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ticket.databinding.ActivityConfirmBinding

class ConfirmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmBinding

    companion object{
        var asal = "asal"
        var tujuan = "tujuan"
        var tanggal = "tanggal"
        var jam = "tahun"
        var jenis = "menit"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityConfirmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var asal = intent.getStringExtra(FormActivity.asal)
        var tujuan = intent.getStringExtra(FormActivity.tujuan)
        var tanggal = intent.getStringExtra(FormActivity.tanggal)
        var jam = intent.getStringExtra(FormActivity.jam)
        var jenis = intent.getStringExtra(FormActivity.jenis)

        with(binding){
            asalText.setText(asal)
            tujuanText.setText(tujuan)
            tanggalText.setText(tanggal)
            jamText.setText(jam)
            jenisText.setText(jenis)

            confirmButton.setOnClickListener{
                Toast.makeText(this@ConfirmActivity, "TIKET BERHASIL TERBELI", Toast.LENGTH_SHORT).show()
            }

            cancelButton.setOnClickListener{
                var intentBack = Intent(this@ConfirmActivity, FormActivity::class.java)
                startActivity(intentBack)
            }
        }
    }
}
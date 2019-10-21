package com.example.huntknow

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.TextView
import android.widget.Toast

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector

class ScanActivity : AppCompatActivity() {

    private lateinit var surfaceScan: SurfaceView
    private lateinit var scanResText: TextView
    private lateinit var qrDetector: BarcodeDetector
    private lateinit var cameraSource: CameraSource
    private var index=0;
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        surfaceScan = findViewById(R.id.surfaceQRScanner)
        scanResText = findViewById((R.id.qrResult))
        qrDetector = BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build()

        if(!qrDetector.isOperational()){
            this.finish()
        }
        fun goToHomeActivityWithResult(){
            intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("qrResult",scanResText.text)
            startActivity(intent)
        }
        qrDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>?) {
                val barcodes = detections?.detectedItems

                if (barcodes!!.size() > 0) {
                    scanResText.post {
                        scanResText.text = barcodes.valueAt(0).displayValue
                        Toast.makeText(getApplicationContext(),scanResText.text,Toast.LENGTH_SHORT).show();
                        index+=1

                        if(scanResText.text!=null && index==1)
                        goToHomeActivityWithResult()
                    }
                }
            }

        })


        cameraSource = CameraSource.Builder(this, qrDetector)
            .setRequestedPreviewSize(2340, 1080)
            .setRequestedFps(25f).setAutoFocusEnabled(true).build()
//
        surfaceScan.holder.addCallback(object:SurfaceHolder.Callback2{
            override fun surfaceRedrawNeeded(holder: SurfaceHolder?) {
            }

            override fun surfaceChanged(
                holder: SurfaceHolder?,
                format: Int,
                width: Int,
                height: Int
            ) {
            }


            override fun surfaceCreated(holder: SurfaceHolder?) {
                if(ContextCompat.checkSelfPermission(this@ScanActivity,
                        Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)
                cameraSource.start(holder)
                else
                    ActivityCompat.requestPermissions(this@ScanActivity, arrayOf(Manifest.permission.CAMERA),111)
            }
            override fun surfaceDestroyed(holder: SurfaceHolder?) {
                cameraSource.stop()
            }
        })

        }
    //
    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==111)
            if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                cameraSource.start(surfaceScan.holder)
    }

    override fun onDestroy() {
        super.onDestroy()
        qrDetector.release()
        cameraSource.stop()
        cameraSource.release()
    }
    }

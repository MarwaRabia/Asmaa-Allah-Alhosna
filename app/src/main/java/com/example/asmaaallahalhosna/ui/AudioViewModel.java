package com.example.asmaaallahalhosna.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.asmaaallahalhosna.R;
import com.example.asmaaallahalhosna.pojo.AudioModel;

import java.util.ArrayList;

public class AudioViewModel extends ViewModel {


    MutableLiveData <ArrayList<AudioModel>>mArrayListMutableLiveData=new MutableLiveData<>();

    public  void getAudioName()
    {
        mArrayListMutableLiveData.setValue(getAudioModel());
    }

    private ArrayList<AudioModel>  getAudioModel(){
        ArrayList<AudioModel>arr=new ArrayList<>();
        arr.add(new AudioModel("الله", R.raw.a1));
        arr.add(new AudioModel("الرحمن",R.raw.a2));
        arr.add(new AudioModel("الرب",R.raw.a3));
        arr.add(new AudioModel("الحليم",R.raw.a4));
        arr.add(new AudioModel("اللطيف",R.raw.a5));
        arr.add(new AudioModel("المهيمن",R.raw.a6));
        arr.add(new AudioModel("الجبار",R.raw.a7));
        arr.add(new AudioModel("السميع",R.raw.a8));
        arr.add(new AudioModel("الفتاح",R.raw.a9));
        arr.add(new AudioModel("الستير",R.raw.a10));
        arr.add(new AudioModel("الحفيظ",R.raw.a11));
        arr.add(new AudioModel("البصير",R.raw.a12));
        arr.add(new AudioModel("الشكور",R.raw.a13));
        arr.add(new AudioModel("السلام",R.raw.a14));
        arr.add(new AudioModel("المحسن",R.raw.a15));
        arr.add(new AudioModel("الحق",R.raw.a16));
        arr.add(new AudioModel("العزيز",R.raw.a17));
        arr.add(new AudioModel("الرقيب",R.raw.a18));
        arr.add(new AudioModel("الحكيم",R.raw.a19));
        arr.add(new AudioModel("الرزاق",R.raw.a20));
        arr.add(new AudioModel("العظيم",R.raw.a21));
        arr.add(new AudioModel("العليم",R.raw.a22));
        arr.add(new AudioModel("القوي",R.raw.a23));
        arr.add(new AudioModel("الغفور",R.raw.a24));
        arr.add(new AudioModel("الكريم",R.raw.a25));
        arr.add(new AudioModel("الملك",R.raw.a26));
        arr.add(new AudioModel("الوكيل",R.raw.a27));
        arr.add(new AudioModel("الهادي",R.raw.a28));
        arr.add(new AudioModel("الوهاب",R.raw.a29));
        arr.add(new AudioModel("التواب",R.raw.a30));
        return  arr;
    }

}

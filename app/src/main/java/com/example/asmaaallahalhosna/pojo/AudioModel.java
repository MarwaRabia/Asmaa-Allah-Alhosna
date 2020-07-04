package com.example.asmaaallahalhosna.pojo;

public class AudioModel {
   String name;
   int audio;

    public AudioModel(String name, int audio) {
        this.name = name;
        this.audio = audio;
    }

    public int getAudio() {
        return audio;
    }

    public void setAudio(int audio) {
        this.audio = audio;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

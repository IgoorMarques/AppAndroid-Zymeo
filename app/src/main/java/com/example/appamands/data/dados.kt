package com.example.appamands.data

import com.example.appamands.modelo.teste

class dados {
    var imgs = ArrayList<teste>()
    init {
        for (i in 0..3){
            imgs.add(teste(i))
        }
    }
}
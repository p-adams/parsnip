package com.example.nlpapp

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import edu.stanford.nlp.simple.*
import org.springframework.web.bind.annotation.*

class Coref {
    var data = ""
}

@RestController
class CorefController {
    fun findCorefs(coref: Coref) {

    }
}
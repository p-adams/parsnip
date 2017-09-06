package com.example.nlpapp

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import edu.stanford.nlp.simple.*
import org.springframework.web.bind.annotation.*

@RestController
class ConstParseController {
    @RequestMapping("/api")
    fun fetchDemoTags(): String {
        val sent = Sentence("colorless green ideas sleep furiously")
        println(sent.parse())
        return sent.parse().toString()
    }
}
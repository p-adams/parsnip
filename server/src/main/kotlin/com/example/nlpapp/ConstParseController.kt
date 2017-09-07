package com.example.nlpapp

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import edu.stanford.nlp.simple.*
import org.springframework.web.bind.annotation.*

@RestController
class ConstParseController {
    @RequestMapping("/api")
    fun fetchDemoTags(): String {
        val sent = Sentence("The quick brown fox jumps over the lazy dog")
        println(sent.parse())
        return sent.parse().skipRoot().toString()
    }
}
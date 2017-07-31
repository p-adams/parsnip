package com.example.nlpapp

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import edu.stanford.nlp.simple.*
import org.springframework.web.bind.annotation.*

class TokData {
    var data = ""
}

@RestController
class TokenizationController {

    @RequestMapping("/api/tokenization")
    fun fetchDemoData(): List<String> {
        return Sentence("Colorless green ideas sleep furiously").words()
    }

    @RequestMapping(value = "/api/tokenization", method = arrayOf(RequestMethod.POST))
    fun getTokenizedText(@RequestBody tokData: TokData): ResponseEntity<List<String>>{
        val tokenized = mutableListOf<String>()
        return ResponseEntity <List<String>>(tokenized, HttpStatus.OK)
    }
}
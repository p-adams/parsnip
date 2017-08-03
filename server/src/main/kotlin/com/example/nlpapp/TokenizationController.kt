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
        return Sentence("\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla consequat elementum metus, vitae molestie ipsum semper a. Nullam sit amet metus tellus. Fusce vitae sem eget nunc sagittis viverra. Pellentesque gravida turpis nunc, ut elementum tellus facilisis vitae. Proin et ornare tellus. Sed ut iaculis lorem. Praesent eros dolor, varius vel arcu vel, iaculis cursus tortor.\n\nPraesent sapien lectus, eleifend quis elementum sed, malesuada in nibh. Proin cursus libero lectus, viverra hendrerit metus. ").words()
    }

    @RequestMapping(value = "/api/tokenization", method = arrayOf(RequestMethod.POST))
    fun getTokenizedText(@RequestBody tokData: TokData): ResponseEntity<List<String>>{
        val tokenized = mutableListOf<String>()
        return ResponseEntity <List<String>>(tokenized, HttpStatus.OK)
    }
}
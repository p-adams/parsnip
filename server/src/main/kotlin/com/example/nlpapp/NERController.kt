package com.example.nlpapp


import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import edu.stanford.nlp.simple.*
import org.springframework.web.bind.annotation.*

class NERData {
    var data = ""
}

@RestController
class NERController {
    fun createLabels(ndata: NERData): MutableMap<String, String> {
        var res = mutableMapOf<String, String>()
        
        return res
    }
    @RequestMapping("/api/ner")
    fun fetchDemo(): MutableMap<String, String> {
        var nerd = NERData()
        nerd.data = "John from Boston worked tirelessly as a mechanic"
        return createLabels(nerd)
    }
}
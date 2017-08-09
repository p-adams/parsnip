package com.example.nlpapp

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import edu.stanford.nlp.simple.*
import org.springframework.web.bind.annotation.*

class POS {
    var data = ""
}

@RestController
class POSController {
    fun fetchPOSTags(p: POS): MutableMap<String, String> {
        var res = mutableMapOf<String, String>()
        var sent = Sentence(p.data)
        var tokens = sent.words()
        val posTags = sent.posTags()
        for ((index, tag) in posTags.withIndex()) {
            res.put(tokens.get(index), tag)

        }
        return res
    }
    @RequestMapping(value = "/api/pos")
    fun getDemoPOSTags(): MutableMap<String, String> {
        var pos = POS()
        pos.data = "Colorless green ideas sleep furiously"
        return fetchPOSTags(pos)

    }
}
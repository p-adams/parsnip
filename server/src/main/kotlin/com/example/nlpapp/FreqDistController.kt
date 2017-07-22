package com.example.nlpapp

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import edu.stanford.nlp.simple.*
import org.springframework.web.bind.annotation.*
import java.util.HashMap



class FreqDist() {
    var data: String = ""
}

@RestController
class FreqDistController {

    fun getTokens(f: FreqDist): Map<String, Long?> {
        val freqs = HashMap<String, Long?>()
        val sent: Sentence = Sentence(f.data)
        val tokens:List<String> = sent.words()
        for (t in tokens) {
            if (freqs.containsKey(t)) {
                freqs.put(t, freqs.get(t)?.plus(1))
            } else {
                freqs.put(t, 1.toLong())
            }
        }
        return freqs
    }

    @RequestMapping(value = "/api/freq-dist", method = arrayOf(RequestMethod.POST))
    fun getFreqDist(@RequestBody f: FreqDist): ResponseEntity <Map<String, Long?>> {
        val tokens: Map<String, Long?> = getTokens(f)
        return ResponseEntity <Map<String, Long?>>(tokens, HttpStatus.OK)
    }
}
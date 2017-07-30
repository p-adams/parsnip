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

    fun getTokens(f: FreqDist): MutableMap<String, Long?> {
        var  freqs = mutableMapOf<String, Long?>()
        val sent: Sentence = Sentence(f.data)
        val tokens:List<String> = sent.words().filterNot{x -> ";:.,?!-'\"(){}".contains(x)}
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
    fun getFreqDist(@RequestBody f: FreqDist): ResponseEntity <MutableMap<String, Long?>> {
        val tokens = getTokens(f)
        return ResponseEntity <MutableMap<String, Long?>>(tokens, HttpStatus.OK)
    }
}


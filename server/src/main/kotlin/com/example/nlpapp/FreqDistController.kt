package com.example.nlpapp

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import edu.stanford.nlp.simple.*
import java.util.HashMap



data class FreqDist(var data: String)

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

    @GetMapping("/api/freq-dist")
    fun getFreqDist(): ResponseEntity <Map<String, Long?>> {
        val f = FreqDist("Colorless green green ideas sleep furiously")
        val tokens: Map<String, Long?> = getTokens(f)
        return ResponseEntity <Map<String, Long?>>(tokens, HttpStatus.OK)
    }
}
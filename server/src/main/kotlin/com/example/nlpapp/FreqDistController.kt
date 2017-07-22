package com.example.nlpapp

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import opennlp.tools.tokenize.TokenizerME
import opennlp.tools.tokenize.TokenizerModel
import java.io.FileNotFoundException
import java.io.FileInputStream
import java.io.InputStream
import java.util.HashMap



data class FreqDist(var data: String)

@RestController
class FreqDistController {

    fun getTokens(f: FreqDist): Map<String, Long?> {
        val freqs = HashMap<String, Long?>()
        var tokens: Array<String>
        var model: InputStream? = null
        try {
            model = FileInputStream("lib/en-token.bin")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        try {
            val m = TokenizerModel(model)
            val tokenizer = TokenizerME(m)
            tokens = tokenizer.tokenize(f.data)
            for (t in tokens) {
                if (freqs.containsKey(t)) {
                    freqs.put(t, freqs.get(t)?.plus(1))
                } else {
                    freqs.put(t, 1.toLong())
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (model != null) {
                try {
                    model!!.close()
                } catch (e: IOException) {

                }

            }
        }

        return freqs
    }

    @GetMapping("/api/freq-dist")
    fun doIt(): ResponseEntity <Map<String, Long?>> {
        val f = FreqDist("Colorless green ideas sleep furiously")
        val tokens: Map<String, Long?> = getTokens(f)
        return ResponseEntity <Map<String, Long?>>(tokens, HttpStatus.OK)
    }
}
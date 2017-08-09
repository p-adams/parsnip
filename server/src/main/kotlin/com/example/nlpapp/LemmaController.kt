package com.example.nlpapp

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import edu.stanford.nlp.simple.*
import org.springframework.web.bind.annotation.*

class Lemma() {
    var data: String = ""
}

@RestController
class LemmaControllerController {

    fun createLemmas(lem: Lemma): MutableMap<String, Long?> {
        var  freqs = mutableMapOf<String, Long?>()
        val sent: Sentence = Sentence(lem.data)
        val lems = sent.lemmas();
        println(lems)
        val tokens:List<String> = sent.lemmas().filterNot{x -> ";:.,?!-'\"(){}".contains(x)}
        for (t in tokens) {
            if (freqs.containsKey(t)) {
                freqs.put(t, freqs.get(t)?.plus(1))
            } else {
                freqs.put(t, 1.toLong())
            }
        }
        return freqs
    }
    @RequestMapping("/api/lemma")
    fun fetchDemoTags(): MutableMap<String, Long?> {
        var lem = Lemma()
        lem.data = "The monkeys jumped from branch to branch"
        return createLemmas(lem)
    }
    @RequestMapping(value = "/api/lemma", method = arrayOf(RequestMethod.POST))
    fun getLemmas(@RequestBody lem: Lemma): ResponseEntity <MutableMap<String, Long?>> {
        val lemmas = createLemmas(lem)
        return ResponseEntity <MutableMap<String, Long?>>(lemmas, HttpStatus.OK)
    }
}

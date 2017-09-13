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
    fun createTags(ndata: NERData): MutableMap<String, String> {
        var res = mutableMapOf<String, String>()
        val sent = Sentence(ndata.data)
        val nerTags = sent.nerTags()
        val tokens = sent.words()
        for ((index, tag) in nerTags.withIndex()) {
            res.put(tokens.get(index), tag)

        }
        println(res)
        return res
    }
    @RequestMapping("/api/ner")
    fun fetchDemoTags(): MutableMap<String, String> {
        var nerd = NERData()
        nerd.data = "John from Boston worked tirelessly as a mechanic for Ford in Detroit"
        return createTags(nerd)
    }
    @RequestMapping(value="/api/ner", method = arrayOf(RequestMethod.POST))
    fun getTags(@RequestBody nerd: NERData): ResponseEntity <MutableMap <String, String>> {
        val result = createTags(nerd)
        return ResponseEntity <MutableMap<String, String>> (result, HttpStatus.OK)
    }
}
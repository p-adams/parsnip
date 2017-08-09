package com.example.nlpapp

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import edu.stanford.nlp.simple.*
import edu.stanford.nlp.trees.Tree
import org.springframework.web.bind.annotation.*

class Parse {
    var data = ""
}

@RestController
class ParseController {
    fun fetchDeps(p: Parse): String{
        val res = Sentence(p.data).parse()
        return res.toString()
    }

    @RequestMapping(value = "/api/parse")
    fun getDemoDeps(): String {
        var p = Parse()
        p.data = "Colorless green ideas sleep furiously"
        val res = fetchDeps(p)
        return res

    }
    @RequestMapping(value = "/api/parse", method = arrayOf(RequestMethod.POST))
    fun getDeps(@RequestBody p: Parse): ResponseEntity <String> {
        val res = fetchDeps(p)
        return ResponseEntity<String>(res, HttpStatus.OK)
    }
}
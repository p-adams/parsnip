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
    fun fetchDeps(p: Parse): Tree {
        val res = Sentence(p.data).parse()
        return res
    }

    @RequestMapping(value = "/api/parse")
    fun getDemoPOSTags(): Tree {
        var p = Parse()
        p.data = "Colorless green ideas sleep furiously"
        return fetchDeps(p)

    }
    @RequestMapping(value = "/api/parse", method = arrayOf(RequestMethod.POST))
    fun getPOSTags(@RequestBody p: Parse): ResponseEntity <Tree> {
        return ResponseEntity<Tree>(fetchDeps(p), HttpStatus.OK)
    }
}
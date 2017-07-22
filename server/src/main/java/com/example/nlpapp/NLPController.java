package com.example.nlpapp;


import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NLPController {

    public String[] doStuff() throws IOException {
        org.slf4j.Logger log = LoggerFactory.getLogger(NlpAppApplication.class);
        String[] s = {"Colorless green ideas sleep furiously, said Noam Chomsky and Burt Reynolds"};
        TokenNameFinderModel m = new TokenNameFinderModel(new File(
                "lib/en-ner-person.bin"
        ));
        NameFinderME f = new NameFinderME(m);
        Tokenizer t = SimpleTokenizer.INSTANCE;
        String[] s1 = {};
        for (String sent : s) {
            String[] tokens = t.tokenize(sent);
            Span[] nameSpans = f.find(tokens);
            s1 = Span.spansToStrings(nameSpans, tokens);
            for (String meow : s1) {
                System.out.println(meow);
            }

        }
        return s1;
    }
    public Map<String, Long> getWordFrequences(NLProcessor p) {
        Map<String, Long> freqs = new HashMap<String, Long>();
        String tokens[] = null;
        InputStream model = null;
        try {
            model = new FileInputStream("lib/en-token.bin");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            TokenizerModel m = new TokenizerModel(model);
            Tokenizer tokenizer = new TokenizerME(m);
            tokens = tokenizer.tokenize(p.getData());
            for(String t : tokens) {
                if(freqs.containsKey(t)) {
                    freqs.put(t, freqs.get(t) + 1);
                } else {
                    freqs.put(t, (long) 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(model != null) {
                try {
                    model.close();
                } catch(IOException e) {

                }
            }
        }

        return freqs;
    }
    @RequestMapping(value = "/meow", method = RequestMethod.GET)
    public String [] meow() {
        try{
            return doStuff();
        } catch(IOException e) {
            String [] foo = {};
            return foo;
        }
    }
    @RequestMapping(value = "/api/freq-dist", method = RequestMethod.GET)

    public String [] api() {
        String[] name = {"foo", "bar", "baz"};
        return name;
    }
    @RequestMapping(value = "/api/freq-dist", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Long>> fetch(@RequestBody NLProcessor p) {
        Map<String, Long> res = getWordFrequences(p);
        return new ResponseEntity<Map<String, Long>>(res, HttpStatus.OK);
    }
}

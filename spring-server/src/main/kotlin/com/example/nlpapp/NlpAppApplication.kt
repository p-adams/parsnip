package com.example.nlpapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class NlpAppApplication

fun main(args: Array<String>) {
    SpringApplication.run(NlpAppApplication::class.java, *args)
}

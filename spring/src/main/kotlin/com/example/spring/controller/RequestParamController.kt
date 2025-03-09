package com.example.spring.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/request_param")
class RequestParamController {

    // curl http://localhost:8080/request_param/query?name=foo
    @GetMapping("/query")
    fun echoQueryParam(
        @RequestParam("name") name: String
    ): Map<String, Any> {
        return mapOf("name" to name)
    }

    // curl http://localhost:8080/request_param/path/12345
    @GetMapping("/path/{id}")
    fun echoPathParam(
        @PathVariable("id") id: String
    ): Map<String, Any> {
        return mapOf("id" to id)
    }

    // curl --json '{"name": "Alice", "message": "Hello"}' http://localhost:8080/request_param/json
    @PostMapping("/json")
    fun echoJsonParam(
        @RequestBody body: Map<String, Any>
    ): Map<String, Any> {
        return body
    }
}
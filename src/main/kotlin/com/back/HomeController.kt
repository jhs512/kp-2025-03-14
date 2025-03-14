package com.back

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController(
    private val s3Service: S3Service
) {
    @GetMapping("/")
    fun main(): String {
        return "Hi"
    }

    @GetMapping("/buckets")
    fun buckets(): List<String> {
        return s3Service.getBucketNames()
    }
}
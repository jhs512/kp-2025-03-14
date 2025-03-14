package com.back;

import org.springframework.stereotype.Service

@Service
class S3ServiceImpl : S3Service {
    override fun getBucketNames(): List<String> {
        return listOf("")
    }
}

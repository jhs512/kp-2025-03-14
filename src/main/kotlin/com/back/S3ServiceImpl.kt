package com.back;

import org.springframework.stereotype.Service
import software.amazon.awssdk.services.s3.S3Client

@Service
class S3ServiceImpl(
    private val s3Client: S3Client
) : S3Service {
    override fun getBucketNames(): List<String> {
        return s3Client.listBuckets().buckets().map { it.name() }
    }
}

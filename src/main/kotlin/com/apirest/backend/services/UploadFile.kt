package com.apirest.backend.services

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path

interface UploadFile
{
    fun upload(fileName: String): Resource
    fun copy(file: MultipartFile): String
    fun delete(fileName: String?): Boolean
    fun getPath(fileName: String): Path
}
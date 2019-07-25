package com.apirest.backend.services

import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

const val DIRECTORY_UPLOAD = "uploads"

@Service
class UploadFileImpl : UploadFile
{
    private val log = LoggerFactory.getLogger(UploadFileImpl::class.java)

    override fun upload(fileName: String): Resource
    {
        var path: Path = getPath(fileName)
        log.info(path.toString())

        var resource: Resource = UrlResource(path.toUri())

        if (!resource.exists() && !resource.isReadable)
        {
            path = Paths.get("src/main/resources/static/images").resolve("no_user.png").toAbsolutePath();
            resource = UrlResource(path.toUri())
            log.error("Error on loading the image $fileName")
        }

        return resource
    }

    override fun copy(file: MultipartFile): String
    {
        val fileName = file.originalFilename!!

        val filePath: Path = getPath(fileName)

        log.info(filePath.toString())


        Files.copy(file.inputStream, filePath)

        return fileName

    }

    override fun delete(fileName: String?): Boolean
    {
        if (fileName != null && fileName.isNotEmpty())
        {
            val imgPathBefore: Path = getPath(fileName)
            val imgFileBefore: File = imgPathBefore.toFile();

            if (imgFileBefore.exists() && imgFileBefore.canRead())
            {
                imgFileBefore.delete()
                return true
            }
        }

        return false
    }

    override fun getPath(fileName: String): Path
    {
        return Paths.get(DIRECTORY_UPLOAD).resolve(fileName).toAbsolutePath()
    }
}
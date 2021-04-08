package com.dsosedov.filepathapi.services;

import com.dsosedov.filepathapi.repositories.FileRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FilePathService {

    private final String nfsMount;

    @Setter
    private FileRepository fileRepository;

    public FilePathService(FileRepository fileRepository, @Value("${nfsMount}") String nfsMount) {
        this.fileRepository = fileRepository;
        this.nfsMount = nfsMount;
    }

    public String validate(String path) {
        if (path == null || Objects.equals(path, "")) {
            return "The file path is missing.";
        }
        if (!path.startsWith(nfsMount)) {
            return "The file path does not belong to the NFS mount.";
        }
        if (!fileRepository.exists(path)) {
            return "The file path is invalid.";
        }
        return "";
    }

}

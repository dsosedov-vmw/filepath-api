package com.dsosedov.filepathapi.services;

import com.dsosedov.filepathapi.components.FileComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FilePathService {

    private final String nfsMount;

    private FileComponent fileComponent;

    public FilePathService(FileComponent fileComponent, @Value("${nfs.mount}") String nfsMount) {
        this.fileComponent = fileComponent;
        this.nfsMount = nfsMount;
    }

    public String validate(String path) {
        if (path == null || Objects.equals(path, "")) {
            return "The file path is missing.";
        }
        if (!path.startsWith("/"+nfsMount)) {
            return "The file path does not belong to the NFS mount.";
        }
        if (!fileComponent.exists(path)) {
            return "The file path is invalid.";
        }
        return "";
    }

}

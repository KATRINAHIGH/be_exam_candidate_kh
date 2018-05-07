package com.scoir.parser.services;

public interface IFileSystemService {
    void ensureDirectoriesExist();
    void watchForChanges();
}

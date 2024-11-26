package com.example.githubtest;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.IOException;

public class GitHubTestRunner {

    private static final String REMOTE_REPO_URL = "https://github.com/AndreaCana04/TPSProva.git"; // Repository GitHub
    private static final String LOCAL_REPO_PATH = "TPSProva-cloned"; // Percorso locale per clonazione

    public static void main(String[] args) {
        try {
            // Step 1: Clonare il repository
            cloneRepository(REMOTE_REPO_URL, LOCAL_REPO_PATH);

            // Step 2: Eseguire i test unitari
            System.out.println("Eseguo i test unitari...");
            runUnitTests(LOCAL_REPO_PATH);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void cloneRepository(String remoteUrl, String localPath) throws GitAPIException {
        System.out.println("Clonazione del repository: " + remoteUrl);
        Git.cloneRepository()
            .setURI(remoteUrl)
            .setDirectory(new File(localPath))
            .call();
        System.out.println("Repository clonato in: " + localPath);
    }

    private static void runUnitTests(String projectPath) throws IOException, InterruptedException {
        // Eseguire il comando Maven per avviare i test
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(new File(projectPath));
        builder.command("mvn", "clean", "test");
        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        builder.redirectError(ProcessBuilder.Redirect.INHERIT);

        Process process = builder.start();
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Test completati con successo!");
        } else {
            System.err.println("I test unitari sono falliti. Codice di uscita: " + exitCode);
        }
    }
}

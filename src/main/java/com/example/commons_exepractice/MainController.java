package com.example.commons_exepractice;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class MainController {
    @PostMapping()
    public ResponseEntity testPython() {
        String [] command = new String[4];
        command[0] = "python";
        command[1] = "D://test.py";
        command[2] = "blob:http://localhost:3000/0fe53945-23d8-4627-9156-580316980f10";
        command[3] = "asdfkjsdfijeksjldfj";
        try {
            return ResponseEntity.ok().body(execPython(command));
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public static String execPython(String[] command) throws IOException, InterruptedException {
        CommandLine commandLine = CommandLine.parse(command[0]);
        for (int i = 1, n = command.length; i<n; i++) {
            commandLine.addArgument(command[i]);
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(pumpStreamHandler);
        int result = executor.execute(commandLine);
        System.out.println("result : " +  result);
        System.out.println("output : " + outputStream.toString());
        return outputStream.toString();
    }
}

package com.codernaught.specgen.commands;

import com.codernaught.specgen.lib.Context;
import org.jline.terminal.Terminal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.shell.command.CommandRegistration;

import java.io.IOException;

@Configuration
public class GenerateCommands {

    Logger logger = LoggerFactory.getLogger(GenerateCommands.class);
    private Terminal terminal;

    public GenerateCommands(Terminal terminal) {
        this.terminal = terminal;
    }

    @Bean
    CommandRegistration generate() {
        return CommandRegistration.builder()
                .command("generate")
                .description("Generate Spring 6 HTTP Web Interface Client code from a swagger specification")
                .group("Generate Commands")
                .withHelpOptions()
                    .enabled(true)
                    .longNames("help")
                    .shortNames('h')
                    .command("help")
                    .and()
                .withOption()
                    .longNames("apiSpec")
                    .description("The name of the api spec file.")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.EXACTLY_ONE)
                    .position(0)
                    .required()
                    .and()
                .withOption()
                    .longNames("stringTemplate")
                    .description("The name of the string template file.")
                    .type(String.class)
                    .arity(CommandRegistration.OptionArity.EXACTLY_ONE)
                    .position(1)
                    .required()
                    .and()
                .withTarget()
                    .function(ctx -> {
                        terminal.writer().println("Generating Spring 6 HTTP Web Interface Client code from a swagger specification");
                        terminal.writer().println("apiSpec file: " + ctx.getOptionValue("apiSpec"));
                        terminal.writer().println("stringTemplate file: " + ctx.getOptionValue("stringTemplate"));
                        terminal.writer().flush();

                        try {
                            FileSystemResource fileSystemResource = new FileSystemResource(ctx.getOptionValue("apiSpec").toString());
                            Context context = new Context(fileSystemResource);
                            context.process();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        return 0;
                    })
                    .and()
                .build();
    }
}

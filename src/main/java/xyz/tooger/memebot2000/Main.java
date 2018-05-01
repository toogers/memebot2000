package xyz.tooger.memebot2000;

import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JDA3Handler;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import xyz.tooger.memebot2000.command.Help;

public class Main {
    public static CommandHandler commandHandler;

    public static void main(String[] args) {
        try {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(System.getenv("token"))
                    .buildBlocking();
            commandHandler = new JDA3Handler(jda);
            registerCommands();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void registerCommands() {
        CommandExecutor[] commands = new CommandExecutor[] {
                new Help()
        };

        for (CommandExecutor command : commands) commandHandler.registerCommand(command);
    }
}

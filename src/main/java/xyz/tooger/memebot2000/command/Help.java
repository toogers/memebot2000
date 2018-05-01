package xyz.tooger.memebot2000.command;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import xyz.tooger.memebot2000.Main;

import java.awt.*;
import java.util.Arrays;

public class Help implements CommandExecutor {
    @Command(
            aliases = "^^help",
            usage = "help [command]",
            description = "List all commands."
    )
    public void onCommand(Message message, String[] args) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.decode("#FFA500"));
        if (args.length == 1) {
            embed.setTitle(args[0]);
            embed.addBlankField(false);
            embed.setFooter(String.format(
                    "Requested by %s#%s",
                    message.getAuthor().getName(),
                    message.getAuthor().getDiscriminator()
                    ), message.getAuthor().getAvatarUrl());
            Main.commandHandler.getCommands().forEach(command -> {
                Command cmd = command.getCommandAnnotation();
                if (Arrays.asList(cmd.aliases()).contains(args[0].toLowerCase())) {
                    embed.addField(
                            cmd.aliases()[0],
                            String.format(
                                    "%s\n`%s`",
                                    cmd.description(),
                                    cmd.usage()
                            ),
                            false
                    );
                }
            });
        } else {
            embed.setTitle("Commands");
            embed.addBlankField(false);
            embed.setFooter(String.format(
                    "Requested by %s#%s",
                    message.getAuthor().getName(),
                    message.getAuthor().getDiscriminator()
            ), message.getAuthor().getAvatarUrl());
            Main.commandHandler.getCommands().forEach(command -> {
                Command cmd = command.getCommandAnnotation();
                embed.addField(
                        cmd.aliases()[0],
                        String.format(
                                "%s\n`%s`",
                                cmd.description(),
                                cmd.usage()
                        ),
                        false
                );
            });
        }
        message.getChannel().sendMessage(embed.build()).queue();
    }
}

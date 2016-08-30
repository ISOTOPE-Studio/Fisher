package cc.isotopestudio.Fisher;

import org.bukkit.entity.Player;

/*
 * Created by Mars on 8/29/2016.
 * Copyright ISOTOPE Studio
 */
public class Reward {

    private final String command;
    private final String msg;

    public Reward(String command, String msg) {
        this.command = command;
        this.msg = msg;
    }

    public String getCommand(Player player) {
        return command.replace("<player>", player.getName());
    }

    public String getMsg() {
        return msg;
    }
}

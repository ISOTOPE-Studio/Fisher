package cc.isotopestudio.Fisher;

/**
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

    public String getCommand() {
        return command;
    }

    public String getMsg() {
        return msg;
    }
}

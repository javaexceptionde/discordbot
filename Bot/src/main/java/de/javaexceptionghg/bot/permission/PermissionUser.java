package de.javaexceptionghg.bot.permission;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class PermissionUser extends IPermissionUser {

    private String id;
    private ArrayList<String> permission = new ArrayList<>();
    private ArrayList<IPermissionGroup> groups = new ArrayList<>();


}

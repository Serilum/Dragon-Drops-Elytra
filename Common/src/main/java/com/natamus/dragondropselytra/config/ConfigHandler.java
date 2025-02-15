package com.natamus.dragondropselytra.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.dragondropselytra.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
    public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

    @Entry public static String elytraEnchantmentList = "\"\"";

    public static void initConfig() {
        configMetaData.put("elytraEnchantmentList", Arrays.asList(
                "Add enchantments for the elytra dropped by dragons to have, for example: \"vanishing_curse:1, unbreaking:3\""
        ));

        DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
    }
}
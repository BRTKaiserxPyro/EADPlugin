package org.brtstp.eazyairdropplugin;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class EazyAirDropPlugin extends JavaPlugin {

package org.brtstp.eazyairdropplugin;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

    public final class EazyAirDropPlugin extends JavaPlugin {

package com.example.eazyairdrop;

import org.bukkit.plugin.java.JavaPlugin;

        public class EazyAirDropPlugin extends JavaPlugin {

            @Override
            public void onEnable() {
                getLogger().info(ChatColor.GOLD"EazyAirDropPlugin", org.bukkit.ChatColor.GREEN"успешно загрузился!");
            }

            @Override
            public void onDisable() {
                getLogger().info(ChatColor.GOLD"EazyAirDropPlugin", ChatColor.RED"выключен.");
            }
            private Location generateTreasureSpawnLocation(Player player) {
                Random random = new Random();

                int x = random.nextInt(1000) - 500;
                int z = random.nextInt(1000) - 500;

                Location location = player.getLocation().clone();
                location.add(x, 0, z);

                // Find the highest block at this location.
                while (location.getBlock().getType() == Material.AIR && location.getY() > 0) {
                    location.subtract(0, 1, 0);
                }

                // If we've reached bedrock or an invalid height,
                // just use the original generated position.
                if (location.getY() <= 0 || !isSuitableBlock(location.getBlock())) {
                    return player.getLocation();
                }

                return location.add(0,1,0); // Add one to Y coordinate to spawn on top of the surface block.
            }
            private boolean isSuitableBlock(Block block) {
                Material type = block.getType();
                return type == Material.GRASS_BLOCK || type == Material.DIRT ||
                        type == Material.COARSE_DIRT || type == Material.PODZOL;
                        type == Material.STONE || type == Material.SAND;
            }

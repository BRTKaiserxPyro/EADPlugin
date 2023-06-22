package org.brtstp.eazyairdropplugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class EazyAirDropPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info(ChatColor.GOLD + "EazyAirDropPlugin" + ChatColor.GREEN + " успешно загрузился!");
        getCommand("eadp spawn").setExecutor(this);
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.GOLD + "EazyAirDropPlugin" + ChatColor.RED + " выключен.");
    }

    private boolean isSuitableBlock(Block block) {
        Material type = block.getType();
        return type == Material.GRASS_BLOCK || type == Material.DIRT ||
                type == Material.COARSE_DIRT || type == Material.PODZOL ||
                type == Material.STONE || type == Material.SAND;
        // Исправлено условие. Добавление знака && между материалами было удалено и добавлено правильное условие.
    }

    private Location generateTreasureSpawnLocation(Player player) {
        Random random = new Random();

        int x = random.nextInt(1000) - 500;
        int z = random.nextInt(1000) - 500;

        Location location = player.getLocation().clone();
        location.add(x, 0, z);

        // Исправлена логика цикла while: мы проверяем блок под локацией на пригодность и уменьшаем координату Y до тех пор, пока не найдем пригодный блок или не достигнем нижней границы мира.
        while (location.getBlock().getType() != Material.AIR && location.getY() > 0) {
            Block block = location.getBlock();
            if (isSuitableBlock(block.getRelative(BlockFace.DOWN))) {
                break;
            }
            location.subtract(0, 1, 0);
        }

        // Если мы достигли уровня ниже или равного нулю, то возвращаем текущую позицию игрока.
        if (location.getY() <= 0) {
            return player.getLocation();
        }

        // Получение блока под локацией
        Block block = location.getBlock().getRelative(BlockFace.DOWN);

        // Проверка на пригодность блока
        if (!isSuitableBlock(block)) {
            return player.getLocation();
        }

        return location;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("eadp")){
            Player player = null;

            if(sender instanceof Player){
                player=(Player)sender;
            }

            if(args[0].equalsIgnoreCase("spawn")){
                Location chestLocation = generateTreasureSpawnLocation(player);

                createChest(chestLocation);

                return true;
            }
        }

        return false;
    }

    private void createChest(Location loc){
        loc.getBlock().setType(Material.CHEST);
    }
}

package io.github.joffrey4.compressedblocks.event;

import io.github.joffrey4.compressedblocks.Main;
import io.github.joffrey4.compressedblocks.api.NMS;
import io.github.joffrey4.compressedblocks.block.RegisterBlocks;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class EventOnBreak extends EventBase implements Listener {

    private NMS nmsHandler;

    public EventOnBreak(Main plugin, NMS nmsHandler) {
        super(plugin);
        this.nmsHandler = nmsHandler;
    }

    /**
     * Placed compressed blocks drops a random stack of uncompressed blocks when they are destroyed.
     * @param event BlockBreakEvent
     */
    @EventHandler
    public void onBreak (BlockBreakEvent event) {
        ImmutableTriple breakResult = nmsHandler.eventOnBreak(event);
        if ((Boolean) breakResult.getLeft()) {

            // Reset the block to AIR to avoid it to drop anything
            event.getBlock().setType(Material.AIR);

            ItemStack dropStack = RegisterBlocks.getUnCompressedBlocks((String) breakResult.getRight(), event.getPlayer());
            event.getBlock().getWorld().dropItemNaturally((Location) breakResult.getMiddle(), dropStack);
        }
    }
}

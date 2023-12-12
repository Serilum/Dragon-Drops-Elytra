package com.natamus.dragondropselytra.neoforge.events;

import com.natamus.dragondropselytra.events.DragonEvent;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeDragonEvent {
	@SubscribeEvent
	public static void mobItemDrop(LivingDropsEvent e) {
		Entity entity = e.getEntity();
		DragonEvent.mobItemDrop(entity.level(), entity, e.getSource());
	}
}

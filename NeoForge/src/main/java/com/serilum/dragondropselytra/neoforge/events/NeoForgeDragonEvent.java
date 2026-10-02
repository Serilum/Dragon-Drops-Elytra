package com.serilum.dragondropselytra.neoforge.events;

import com.serilum.dragondropselytra.events.DragonEvent;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.bus.api.SubscribeEvent;

public class NeoForgeDragonEvent {
	@SubscribeEvent
	public static void mobItemDrop(LivingDropsEvent e) {
		Entity entity = e.getEntity();
		DragonEvent.mobItemDrop(entity.level(), entity, e.getSource());
	}
}

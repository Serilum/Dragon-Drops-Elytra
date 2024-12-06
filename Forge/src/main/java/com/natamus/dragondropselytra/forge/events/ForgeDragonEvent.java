package com.natamus.dragondropselytra.forge.events;

import com.natamus.dragondropselytra.events.DragonEvent;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeDragonEvent {
	@SubscribeEvent
	public void mobItemDrop(LivingDropsEvent e) {
		Entity entity = e.getEntity();
		DragonEvent.mobItemDrop(entity.level(), entity, e.getSource());
	}
}

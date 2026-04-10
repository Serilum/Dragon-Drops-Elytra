package com.natamus.dragondropselytra.forge.events;

import com.natamus.dragondropselytra.events.DragonEvent;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeDragonEvent {
	public static void registerEventsInBus() {
		// BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeDragonEvent.class);

		LivingDropsEvent.BUS.addListener(ForgeDragonEvent::mobItemDrop);
	}

	@SubscribeEvent
	public static void mobItemDrop(LivingDropsEvent e) {
		Entity entity = e.getEntity();
		DragonEvent.mobItemDrop(entity.level(), entity, e.getSource());
	}
}
